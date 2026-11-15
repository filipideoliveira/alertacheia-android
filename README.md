# AlertaCheia · Android

App Android do AlertaCheia, feito em Kotlin com Jetpack Compose para a Global Solution 2026.2 da FIAP.

## Desenvolvedores

| Nome completo | RM |
| --- | --- |
| Filipi de Oliveira | 555484 |
| João Gabriel Silva dos Santos | 575198 |

Equipe CulturaC6, turma 3SIZ.

## Tema da Global Solution

Desafio FIAP CitizenTech: uma solução tecnológica para um problema real de quem vive na cidade. O nosso problema são as enchentes.

## A solução

Quando a chuva aperta, o alerta de enchente chega no celular de todo mundo. O problema começa logo depois: pra onde eu vou? Qual abrigo ainda tem vaga? Dá pra levar o cachorro?

O AlertaCheia responde isso numa tela só. O morador desalojado vê as regiões em alerta, os abrigos indicados para chegar com segurança e a lista completa de abrigos, que ele filtra pelo que precisa.

## Funcionalidades

- **Alerta de enchente:** uma faixa no topo mostra as regiões em alerta, Centro e Zona Leste.
- **Rota segura:** uma faixa horizontal com os abrigos que têm vaga, ficam numa região em alerta e não têm rua alagada no caminho. Os destaques saem de uma regra no repositório, não de uma escolha manual.
- **Filtro por necessidade:** chips com Todas, Acessível, Aceita pet, Atendimento médico, Bebês e crianças e Roupa seca e banho. Ao tocar num chip, a lista e o título mudam na hora.
- **Lista de abrigos:** cada card mostra foto, nome, bairro e região, atendimentos, uma barra de ocupação e as vagas livres. A lista vem ordenada por vagas, e abrigo lotado vai para o fim.
- **Vagas em destaque:** com 10 vagas ou menos, a barra e o texto ficam vermelhos. Abrigo lotado mostra "Lotado · 150 de 150" e nunca entra na Rota segura.

## Fluxo de telas

O app tem uma tela, a `HomeScreen`, que se lê de cima para baixo:

1. Abre direto no alerta da região e na Rota segura.
2. O morador responde "Do que você precisa?" tocando num chip.
3. A lista abaixo mostra só os abrigos com aquele atendimento, com a contagem no título.
4. Tocar em "Todas" volta para a lista completa.

## Prints

| Início | Filtro "Aceita pet" | Fim da lista |
| --- | --- | --- |
| ![Início](docs/prints/01-inicio.png) | ![Filtro Aceita pet](docs/prints/02-filtro-aceita-pet.png) | ![Fim da lista](docs/prints/03-lista-lotado.png) |

## Fonte dos dados

Os dados ficam num repositório em memória, a classe `AbrigoRepository`, com 8 abrigos fictícios da cidade de São Paulo. O app não usa API nem banco.

As regiões em alerta, Centro e Zona Leste, são as mesmas que a consulta de regiões críticas da entrega de Banco de Dados da GS aponta. As fotos vêm do [Unsplash](https://unsplash.com) por URL e são carregadas com a biblioteca Coil.

## Arquitetura e packages

```
br.com.filipideoliveira.alertacheia
├── MainActivity.kt            abre o app no tema e na HomeScreen
├── model/
│   ├── Categoria.kt           enum com os 5 atendimentos
│   └── Abrigo.kt              data class com capacidade, ocupação, categorias e destaque
├── data/
│   └── AbrigoRepository.kt    dados em memória, filtro, ordenação e regra da Rota segura
└── ui/
    ├── components/
    │   ├── AbrigoListItem.kt      card de cada abrigo na lista vertical
    │   ├── CategoriaSelector.kt   fileira de chips do filtro
    │   └── RotaSeguraSection.kt   faixa de alerta e destaques horizontais
    ├── screens/
    │   └── HomeScreen.kt          junta tudo e guarda a categoria escolhida
    └── theme/
        ├── Color.kt               azul oceano e vermelho de alerta
        └── Theme.kt               esquema de cores do Material 3
```

A regra fica separada da tela. O repositório filtra, ordena e decide os destaques. A `HomeScreen` guarda a categoria escolhida com `remember { mutableStateOf() }` e pede a lista ao repositório. Os componentes só desenham o que recebem.

**Tecnologias:** Kotlin, Jetpack Compose, Material 3 e Coil 3.

## Como rodar

1. Abrir a pasta do projeto no Android Studio.
2. Esperar o Gradle sincronizar.
3. Rodar o módulo `app` num emulador ou celular com Android 7.0 ou superior e acesso à internet, que é usado para carregar as fotos.

## Vídeo pitch

Link do vídeo: a definir
