package com.sembanha.smokehouse.data

object MockData {
    val categories = listOf(
        Category("todos", "Todos", "🔥"),
        Category("favoritos", "Favoritos da Brasa", "⭐"),
        Category("cortes", "Cortes Nobres", "🥩"),
        Category("petiscos", "Porções & Petiscos", "🥓"),
        Category("pratos", "Pratos da Casa", "🥘"),
        Category("bebidas", "Chopp & Bebidas", "🍺"),
        Category("sobremesas", "Sobremesas", "🍮")
    )

    val dishes = listOf(
        Dish(
            id = "costela-bbq",
            name = "Costela BBQ Defumada 12h",
            category = "favoritos",
            price = 109.90,
            imageAsset = "file:///android_asset/images/costela-bbq.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?w=600&auto=format&fit=crop&q=80",
            badges = listOf("DEFUMADO A LENHA", "RECEITA DE ASSINATURA"),
            serving = "Serve 2 pessoas",
            weight = "700g · Cozimento Lento",
            description = "Costela suína selecionada, marinada em especiarias artesanais e defumada lentamente em lenha de macieira por 12 horas. Glaceada com molho barbecue levemente picante.",
            acompanhamentos = listOf(
                SideOption("arroz-carreteiro", "Arroz Carreteiro", "Tradicional no disco de arado com carne seca"),
                SideOption("mandioca-manteiga", "Mandioca na Manteiga de Garrafa", "Cozida, ultra macia e finalizada na brasa"),
                SideOption("farofa-castanha", "Farofa Crocante de Castanha", "Farinha artesanal, cebola dourada e castanhas"),
                SideOption("vinagrete-roca", "Vinagrete de Roça", "Tomates frescos, cebola roxa e azeite extravirgem")
            ),
            molhos = listOf(
                SauceOption("chimichurri", "Chimichurri Brasileiro com Ervas Frescas", 0.0),
                SauceOption("molho-lambao", "Molho Lambão com Pimenta Biquinho", 4.50),
                SauceOption("geleia-pimenta", "Geleia de Pimenta Defumada da Casa", 4.50)
            )
        ),
        Dish(
            id = "feijoada-completa",
            name = "Feijoada Completa da Casa",
            category = "pratos",
            price = 89.90,
            imageAsset = "file:///android_asset/images/feijoada.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=600&auto=format&fit=crop&q=80",
            badges = listOf("CLÁSSICO BRASILEIRO", "ESPECIAL NO FOGO"),
            serving = "Serve 2 a 3 pessoas",
            weight = "Panela de Barro Artesanal",
            description = "Tradicional feijoada em panela de barro com carnes nobres defumadas, arroz branco, couve refogada no alho, farofa crocante e torresmo pururuca.",
            acompanhamentos = listOf(
                SideOption("arroz-branco", "Arroz Branco Soltinho", "Clássico brasileiro cozido ao dente"),
                SideOption("farofa-bacon", "Farofa de Bacon Crocante", "Dourada com alho tostado"),
                SideOption("couve", "Couve Manteiga Fatiada", "Refogada com alho na brasa")
            ),
            molhos = listOf(
                SauceOption("molho-pimenta", "Molho de Pimenta da Feijoada", 0.0)
            )
        ),
        Dish(
            id = "baiao-de-dois",
            name = "Baião de Dois do Sertão",
            category = "pratos",
            price = 74.90,
            imageAsset = "file:///android_asset/images/baiao.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=600&auto=format&fit=crop&q=80",
            badges = listOf("TRADIÇÃO NORDESTINA", "RECEITA RAIZ"),
            serving = "Serve 2 pessoas",
            weight = "Receita Autêntica",
            description = "Feijão-de-corda refogado com arroz da terra, carne seca desfiada suculenta, queijo coalho tostado na brasa, manteiga de garrafa e cheiro-verde fresco.",
            acompanhamentos = listOf(
                SideOption("mandioca-frita", "Mandioca Cremosa Frita", "Crocante por fora e macia por dentro")
            ),
            molhos = listOf(
                SauceOption("pimenta-biquinho", "Pimenta Biquinho Fresca", 0.0)
            )
        ),
        Dish(
            id = "escondidinho-carne-seca",
            name = "Escondidinho Gratinado",
            category = "pratos",
            price = 68.00,
            imageAsset = "file:///android_asset/images/escondidinho.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=600&auto=format&fit=crop&q=80",
            badges = listOf("FORNO A LENHA", "GRATINADO NA BRASA"),
            serving = "Individual generoso",
            weight = "Cumbuca de Barro",
            description = "Cremoso purê de mandioca com recheio farto de carne seca desfiada temperada, gratinado com queijo parmesão e catupiry borbulhante."
        ),
        Dish(
            id = "strogonoff-carne",
            name = "Strogonoff Tradicional de Filé",
            category = "pratos",
            price = 64.50,
            imageAsset = "file:///android_asset/images/strogonoff.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1534422298391-e4f8c172dddb?w=600&auto=format&fit=crop&q=80",
            badges = listOf("PREFERIDO DA FAMÍLIA", "FILÉ MIGNON"),
            serving = "Serve 1 a 2 pessoas",
            weight = "Cortes Nobres",
            description = "Tiras de filé mignon grelhadas no fogo alto, molho cremoso com cogumelos frescos, acompanhado de arroz branco e batata palha artesanal."
        ),
        Dish(
            id = "torresmo-rolo",
            name = "Torresmo de Rolo Pururuca",
            category = "petiscos",
            price = 58.90,
            imageAsset = "file:///android_asset/images/mural_costela.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1529692236671-f1f6cf9683ba?w=600&auto=format&fit=crop&q=80",
            badges = listOf("CROCANTE", "PETISCO DA CASA"),
            serving = "Para compartilhar",
            weight = "450g",
            description = "Barriga suína enrolada, assada lentamente por 6 horas e pururucada. Acompanha geleia de pimenta defumada da casa."
        ),
        Dish(
            id = "chopp-artesanal",
            name = "Chopp Puro Malte Artesanal 500ml",
            category = "bebidas",
            price = 18.50,
            imageAsset = "file:///android_asset/images/mural_chopp.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1608270172608-518258457007?w=600&auto=format&fit=crop&q=80",
            badges = listOf("CANECA CONGELADA -2°C", "PURO MALTE"),
            serving = "500ml",
            weight = "Pilsen Especial",
            description = "Servido em caneca ultracongelada a -2°C com colarinho cremoso e persistente."
        ),
        Dish(
            id = "chimarrao",
            name = "Chimarrão Artesanal Gaúcho",
            category = "bebidas",
            price = 22.00,
            imageAsset = "file:///android_asset/images/chimarrao.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600&auto=format&fit=crop&q=80",
            badges = listOf("EXPERIÊNCIA DO SUL", "REFIL LIVRE"),
            serving = "Individual + Garrafa Térmica",
            weight = "Cuia de Porongo Tradicional",
            description = "Autêntica cuia tradicional de porongo com bomba de alpaca, erva-mate pura folha e água quente na temperatura exata (75°C)."
        ),
        Dish(
            id = "pudim-leite",
            name = "Pudim de Leite na Brasa",
            category = "sobremesas",
            price = 26.00,
            imageAsset = "file:///android_asset/images/mural_3.jpg",
            fallbackUrl = "https://images.unsplash.com/photo-1587314168485-3236d6710814?w=600&auto=format&fit=crop&q=80",
            badges = listOf("SOBREMESA EXCLUSIVA", "CARAMELO ÂMBAR"),
            serving = "Fatia generosa",
            weight = "Receita de Vó no Fogo",
            description = "Pudim artesanal de leite condensado assado no forno a lenha com calda de caramelo âmbar e raspas de limão caipira."
        )
    )

    val initialOrders = listOf(
        OrderItem(
            id = "ord-1",
            dishId = "feijoada-completa",
            name = "Feijoada Completa da Casa",
            tag = "Acompanha arroz, couve e farofa",
            servingType = "Individual",
            unitPrice = 89.90,
            qty = 1,
            deliveredTime = "Entregue às 20:05"
        ),
        OrderItem(
            id = "ord-2",
            dishId = "torresmo-rolo",
            name = "Torresmo de Rolo Pururuca",
            tag = "Com geleia de pimenta da roça",
            servingType = "Petisco",
            unitPrice = 58.90,
            qty = 1,
            deliveredTime = "Entregue às 19:58"
        ),
        OrderItem(
            id = "ord-3",
            dishId = "baiao-de-dois",
            name = "Baião de Dois do Sertão",
            tag = "Queijo coalho tostado & carne de sol",
            servingType = "Para 2 Pessoas",
            unitPrice = 74.90,
            qty = 1,
            deliveredTime = "Entregue às 20:10"
        ),
        OrderItem(
            id = "ord-4",
            dishId = "chopp-artesanal",
            name = "Chopp Puro Malte 500ml",
            tag = "Caneca ultracongelada (500ml)",
            servingType = "R$ 18,50 cada",
            unitPrice = 18.50,
            qty = 2,
            deliveredTime = "Entregue às 19:48"
        )
    )

    val initialStamps = listOf(
        Stamp(1, "15/JAN", "Mesa 04", "Lucas", true),
        Stamp(2, "02/FEB", "Mesa 12", "Mateus", true),
        Stamp(3, "14/FEB", "Mesa 14", "Lucas", true),
        Stamp(4, "02/MAR", "Mesa 08", "Júlia", true),
        Stamp(5, "17/MAR", "Mesa 14", "Lucas", true),
        Stamp(6, "05/ABR", "Mesa 22", "Thiago", true),
        Stamp(7, "18/ABR", "Mesa 14", "Lucas", true),
        Stamp(8, "HOJE", "Mesa 14", "Lucas", false)
    )

    val muralMemories = listOf(
        MuralItem(
            id = "m-1",
            title = "Churrascada Final",
            subtitle = "Mesa 14 · Salão Principal",
            date = "18 de Abr, 2025",
            imageAsset = "file:///android_asset/images/toast-friends.jpg",
            tag = "#Mesa14 #FogoEbrasa"
        ),
        MuralItem(
            id = "m-2",
            title = "Sexta dos Campeões",
            subtitle = "Mesa 22 · Deck Brasa",
            date = "05 de Abr, 2025",
            imageAsset = "file:///android_asset/images/mural_1.jpg",
            tag = "#NoiteEpica #Costela12h"
        ),
        MuralItem(
            id = "m-3",
            title = "Costela & Amigos",
            subtitle = "Mesa 08 · Lounge BBQ",
            date = "02 de Mar, 2025",
            imageAsset = "file:///android_asset/images/mural_2.jpg",
            tag = "#PitmasterClub"
        ),
        MuralItem(
            id = "m-4",
            title = "Rodada de Chopp & Brasa",
            subtitle = "Mesa 12 · Salão",
            date = "02 de Fev, 2025",
            imageAsset = "file:///android_asset/images/mural_chopp.jpg",
            tag = "#SemBanhaBBQ"
        )
    )
}
