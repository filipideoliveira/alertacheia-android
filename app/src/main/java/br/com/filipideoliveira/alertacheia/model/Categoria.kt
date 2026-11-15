package br.com.filipideoliveira.alertacheia.model

// O que o abrigo oferece. Viram os chips do filtro.
// "Todas" não entra aqui: ela é representada por null na tela.
enum class Categoria(val label: String) {
    ACESSIVEL("Acessível"),
    ACEITA_PET("Aceita pet"),
    ATENDIMENTO_MEDICO("Atendimento médico"),
    BEBES_E_CRIANCAS("Bebês e crianças"),
    ROUPA_SECA_E_BANHO("Roupa seca e banho")
}
