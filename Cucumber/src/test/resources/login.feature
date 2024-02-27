Feature: Validación funcionalidad Login

  Scenario Outline: Verificar login de la aplicacion
    Given me encuentro en la pagina de login
    When realizo el login con el user "<user>" y el pass "<pass>"
    Then me encuentro en la home de la app

    Examples:
      | user            | pass         |
      | standard_user   | secret_sauce |
      | problem_user    | secret_sauce |

  Scenario Outline: Verificar mensaje de error de login fallido
    Given me encuentro en la pagina de login
    When realizo el login con el user "<user>" y el pass "<pass>"
    Then visualizo el mensaje de error

    Examples:
      | user            | pass         |
      | locked_out_user | secret_sauce |