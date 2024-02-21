# Hexagonal Architecture Sample
This is the sample Spring Boot application used to demonstrate how to leverage the Ports and Adapters pattern by Alistair Cockburn in my YouTube video: <link here>

![Hexagonal High Level Diagram](hexagonal%20high%20level.png)

## Overview
The app supports only one use-case: withdrawing money from a bank account with the only rules being that:
* The account number must exist.
* The account balance can never be negative.

### The Business Logic
All the business logic has been placed in the [eu.marcolenzo.hexagonalarchitecture.core](src/main/java/eu/marcolenzo/hexagonalarchitecture/core) package. This represents the inner hexagon in the diagram. In this package, we use plain Java and we avoid using frameworks (including Spring) or libraries.

### Ports
The app has only two ports.

The primary port is the [WithdrawFundsUseCase](src/main/java/eu/marcolenzo/hexagonalarchitecture/core/usecases/WithdrawFundsUseCase.java) interface that models our use case and raises an exception if the account is unknown or has insufficient funds, which covers our requirements.

This primary port is implemented as part of the business logic as a service class: [AccountServices](src/main/java/eu/marcolenzo/hexagonalarchitecture/core/domain/AccountServices.java)

The secondary port is the [AccountRepository.java](src/main/java/eu/marcolenzo/hexagonalarchitecture/core/repositories/AccountRepository.java) interface which allows to interact with a persistence layer. 

### Adapters
[InMemoryAccountRepository](src/main/java/eu/marcolenzo/hexagonalarchitecture/persistence/memory/InMemoryAccountRepository.java) and [AccountRepositoryImpl](src/main/java/eu/marcolenzo/hexagonalarchitecture/persistence/mongo/AccountRepositoryImpl.java) are two adapters implementing the same [AccountRepository.java](src/main/java/eu/marcolenzo/hexagonalarchitecture/core/repositories/AccountRepository.java) port. They showcase the power of the pattern that allow use to have multiple adapters for the same port.

It is also important to have a look at the [AccountDocument](src/main/java/eu/marcolenzo/hexagonalarchitecture/persistence/mongo/AccountDocument.java) class. The properties are identical to our [Account](src/main/java/eu/marcolenzo/hexagonalarchitecture/core/domain/Account.java) class, but it is necessary to have distinct documents to avoid that we create a hard dependency between our business logic in the `core` package and the Spring Data framework.

The primary adapter is a simple `@RestController`: [AccountController](src/main/java/eu/marcolenzo/hexagonalarchitecture/web/controllers/AccountController.java)

### Configuration
[DefaultConfiguration](src/main/java/eu/marcolenzo/hexagonalarchitecture/configuration/DefaultConfiguration.java) and [LocalConfiguration](src/main/java/eu/marcolenzo/hexagonalarchitecture/configuration/LocalConfiguration.java) show how to plan different app context configurations for different profiles. When the local profile is active we leverage the in-memory repository instead of the MongoDB one.