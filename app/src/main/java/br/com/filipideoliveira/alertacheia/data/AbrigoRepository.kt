package br.com.filipideoliveira.alertacheia.data

import br.com.filipideoliveira.alertacheia.model.Abrigo
import br.com.filipideoliveira.alertacheia.model.Categoria

// Dados em memória e toda a lógica de filtro ficam aqui, fora da tela.
class AbrigoRepository {

    // Regiões com alerta de enchente agora.
    private val regioesEmAlerta = setOf("Centro", "Zona Leste")

    // Bairros onde o caminho até o abrigo passa por rua alagada.
    private val bairrosComRuaAlagada = setOf("República", "São Miguel Paulista")

    private fun foto(id: String) =
        "https://images.unsplash.com/photo-$id?w=600&q=80&fit=crop"

    // O destaque não vem marcado aqui: é calculado pela regra da Rota segura.
    private val abrigos: List<Abrigo> = listOf(
        Abrigo(
            nome = "Escola Municipal da Sé",
            imagemUrl = foto("1592066575517-58df903152f2"),
            bairro = "Sé",
            regiao = "Centro",
            capacidade = 120,
            ocupacao = 118,
            categorias = listOf(Categoria.ACESSIVEL, Categoria.ATENDIMENTO_MEDICO)
        ),
        Abrigo(
            nome = "Ginásio República",
            imagemUrl = foto("1768554630751-6448593749eb"),
            bairro = "República",
            regiao = "Centro",
            capacidade = 200,
            ocupacao = 90,
            categorias = listOf(Categoria.ACEITA_PET, Categoria.ROUPA_SECA_E_BANHO)
        ),
        Abrigo(
            nome = "Centro Educacional Santana",
            imagemUrl = foto("1591123120675-6f7f1aae0e5b"),
            bairro = "Santana",
            regiao = "Zona Norte",
            capacidade = 150,
            ocupacao = 150,
            categorias = listOf(
                Categoria.ACESSIVEL,
                Categoria.ATENDIMENTO_MEDICO,
                Categoria.BEBES_E_CRIANCAS
            )
        ),
        Abrigo(
            nome = "Escola Estadual Vila Maria",
            imagemUrl = foto("1613896527026-f195d5c818ed"),
            bairro = "Vila Maria",
            regiao = "Zona Norte",
            capacidade = 100,
            ocupacao = 40,
            categorias = listOf(Categoria.BEBES_E_CRIANCAS, Categoria.ROUPA_SECA_E_BANHO)
        ),
        Abrigo(
            nome = "Biblioteca de Itaquera",
            imagemUrl = foto("1780004033644-0a6d7798943c"),
            bairro = "Itaquera",
            regiao = "Zona Leste",
            capacidade = 250,
            ocupacao = 60,
            categorias = listOf(
                Categoria.ACESSIVEL,
                Categoria.ACEITA_PET,
                Categoria.ROUPA_SECA_E_BANHO
            )
        ),
        Abrigo(
            nome = "Escola Municipal São Miguel",
            imagemUrl = foto("1584750153892-38414eb8e76a"),
            bairro = "São Miguel Paulista",
            regiao = "Zona Leste",
            capacidade = 180,
            ocupacao = 120,
            categorias = listOf(Categoria.ATENDIMENTO_MEDICO, Categoria.BEBES_E_CRIANCAS)
        ),
        Abrigo(
            nome = "Centro Comunitário Mooca",
            imagemUrl = foto("1774386897531-1f5d7741409c"),
            bairro = "Mooca",
            regiao = "Zona Leste",
            capacidade = 120,
            ocupacao = 70,
            categorias = listOf(
                Categoria.ACESSIVEL,
                Categoria.ACEITA_PET,
                Categoria.ATENDIMENTO_MEDICO
            )
        ),
        Abrigo(
            nome = "Paróquia Pinheiros",
            imagemUrl = foto("1709794150814-77b9d1f27364"),
            bairro = "Pinheiros",
            regiao = "Zona Oeste",
            capacidade = 80,
            ocupacao = 30,
            categorias = listOf(Categoria.BEBES_E_CRIANCAS, Categoria.ROUPA_SECA_E_BANHO)
        )
    )
        .map { it.copy(destaque = estaNaRotaSegura(it)) }
        // Mais vagas primeiro; lotado vai naturalmente pro fim (0 vagas).
        .sortedByDescending { it.vagasLivres }

    // Rota segura: tem vaga, fica numa região em alerta
    // e dá pra chegar sem passar por rua alagada.
    private fun estaNaRotaSegura(abrigo: Abrigo): Boolean =
        !abrigo.lotado &&
            abrigo.regiao in regioesEmAlerta &&
            abrigo.bairro !in bairrosComRuaAlagada

    fun regioesEmAlerta(): List<String> = regioesEmAlerta.toList()

    fun listar(): List<Abrigo> = abrigos

    fun destaques(): List<Abrigo> = abrigos.filter { it.destaque }

    fun filtrar(categoria: Categoria?): List<Abrigo> =
        if (categoria == null) abrigos
        else abrigos.filter { categoria in it.categorias }
}
