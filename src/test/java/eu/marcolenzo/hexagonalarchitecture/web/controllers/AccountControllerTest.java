package eu.marcolenzo.hexagonalarchitecture.web.controllers;

import eu.marcolenzo.hexagonalarchitecture.core.domain.Account;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.InsufficientFundsException;
import eu.marcolenzo.hexagonalarchitecture.core.exceptions.UnknownAccountException;
import eu.marcolenzo.hexagonalarchitecture.core.usecases.WithdrawFundsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WithdrawFundsUseCase withdrawFundsUseCase;

    @Test
    public void withdrawFundsReturnsAccountResourceWhenSuccessful() throws Exception {
        when(withdrawFundsUseCase.withdrawFunds(anyLong(), any(BigDecimal.class)))
                .thenReturn(new Account(123456L, "1", BigDecimal.valueOf(50)));

        mockMvc.perform(post("/accounts/123456/actions/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("10"))
                .andExpect(status().isOk());
    }

    @Test
    public void withdrawFundsReturnsBadRequestWhenUnknownAccount() throws Exception {
        when(withdrawFundsUseCase.withdrawFunds(anyLong(), any(BigDecimal.class)))
                .thenThrow(new UnknownAccountException("Account not found"));

        mockMvc.perform(post("/accounts/123456/actions/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("10"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void withdrawFundsReturnsBadRequestWhenInsufficientFunds() throws Exception {
        when(withdrawFundsUseCase.withdrawFunds(anyLong(), any(BigDecimal.class)))
            .thenThrow(new InsufficientFundsException("Account not found"));

        mockMvc.perform(post("/accounts/123456/actions/withdraw")
                .contentType(MediaType.APPLICATION_JSON)
                .content("10"))
            .andExpect(status().isBadRequest());
    }
}