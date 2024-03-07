Feature: Verificar la funcionalidad del registro

  Background:
    Given me encuentro en la pagina home
    When hago click en el boton de registro

  Scenario: Registro con datos vacios
    And le doy al boton de continuar
    Then aparece el mensaje de error "Warning: You must agree to the Privacy Policy!"

  Scenario: Registro con datos vacios aceptando la Privacy Policy
    And hago click en el checkbox de PrivacyPolicy
    And le doy al boton de continuar
    Then aparecen todos los mensajes de error de los inputs obligatorios

  Scenario Outline: Registro con los datos correctos
    And realizo el registro con el firstName "<firstName>", lastName "<lastName>", email "<email>", telephone "<telephone>", password "<password>"
    And hago click en el checkbox de PrivacyPolicy
    And le doy al boton de continuar
    Then aparece el siguiente mensaje "Congratulations! Your new account has been successfully created!"

    Examples:
      | firstName | lastName | email                | telephone | password     |
      | Ivan      | Gomez    | ivangomcav@gmail.com | 678987789 | Boligrafo_12 |

#  Scenario: Registro con simbolos en el First Name
#  Scenario: Registro con mas de 32 caracteres en el First Name
#  Scenario: Registro con simbolos en el Last Name
#  Scenario: Registro con mas de 32 caracteres en el Last Name
#  Scenario: Registro con email no valido
#  Scenario: Registro con telefono con menos de 3 caracteres
#  Scenario: Registro con telefono con mas de 32 caracteres
#  Scenario: Registro con telefono con caracteres
#  Scenario: Registro con telefono con mezcla de caracteres y simbolos
#  Scenario: Registro con contrasena de menos de 4 caracteres
#  Scenario: Registro con contrasena de mas de 20 caracteres
#  Scenario: Registro con la contraseña a repetir distinta de la original
