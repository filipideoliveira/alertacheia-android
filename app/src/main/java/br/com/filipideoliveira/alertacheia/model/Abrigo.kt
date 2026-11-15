package br.com.filipideoliveira.alertacheia.model

// Um abrigo pode oferecer várias coisas, por isso List<Categoria>.
// destaque = true coloca o abrigo na Rota segura. Quem decide é o repositório.
data class Abrigo(
    val nome: String,
    val bairro: String,
    val regiao: String,
    val capacidade: Int,
    val ocupacao: Int,
    val categorias: List<Categoria>,
    val imagemUrl: String,
    val destaque: Boolean = false
) {
    val vagasLivres: Int
        get() = (capacidade - ocupacao).coerceAtLeast(0)

    val lotado: Boolean
        get() = vagasLivres == 0
}
