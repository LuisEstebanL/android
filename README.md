# Sem-Banha Smokehouse & Brasa Brasileira — Android Studio App

Aplicativo nativo para Android desenvolvido com **Kotlin**, **Jetpack Compose** e **Material 3**, baseado no projeto Figma do cardápio digital e comanda da mesa.

---

## 📱 Visão Geral da Arquitetura

- **Linguagem**: Kotlin 2.0.21
- **UI Toolkit**: Jetpack Compose com Material 3 (Dark Smokehouse BBQ Theme)
- **Gerenciamento de Estado**: Android Jetpack `ViewModel` com estados reativos Compose (`mutableStateOf`, `mutableStateListOf`)
- **Carregamento de Imagens**: Coil (`io.coil-kt:coil-compose`) com suporte a assets locais e URLs remotas
- **Navegação**: Bottom Navigation com 4 abas integradas (Cardápio, Comanda, Câmera, Selos) + BottomSheet de personalização de prato

---

## 🚀 Como Abrir no Android Studio

1. Abra o **Android Studio**.
2. Clique em **File > Open...** (ou na tela inicial, clique em **Open**).
3. Selecione a pasta:
   ```
   /Users/senai/.gemini/antigravity/scratch/sem-banha/android
   ```
4. Aguarde o Android Studio sincronizar o Gradle (o `local.properties` já aponta para o seu Android SDK em `/Users/senai/Library/Android/sdk`).
5. Conecte um celular Android via USB (com depuração ativada) ou inicie um emulador no Android Studio (Device Manager).
6. Clique no botão verde de **Run** (ou aperte `Shift + F10`).

---

## 🛠️ Compilação via Linha de Comando (Terminal)

Você também pode compilar e gerar o APK de depuração diretamente via terminal:

```bash
cd /Users/senai/.gemini/antigravity/scratch/sem-banha/android
./gradlew assembleDebug
```

O APK gerado fica localizado em:
```
/Users/senai/.gemini/antigravity/scratch/sem-banha/android/app/build/outputs/apk/debug/app-debug.apk
```

Para instalar diretamente em um dispositivo/emulador conectado:
```bash
./gradlew installDebug
```

---

## 📋 Telas e Módulos do App (Figma Mapping)

1. **Cardápio (MenuScreen)**:
   - Banner de fidelidade ("Falta apenas 1 selo para seu brinde!") com progresso de 87% (7/8 selos).
   - Campo de busca instantânea de pratos.
   - Filtros de categorias em chips horizontais (Favoritos, Cortes, Petiscos, Pratos, Bebidas, Sobremesas).
   - Lista de pratos com fotos reais, tags de preparo, preços e botão de adicionar.
   - Barra inferior flutuante da Mesa 14 ativa.

2. **Personalização do Prato (DishDetailSheet - BottomSheet)**:
   - Escolha obrigatória de 1 acompanhamento (Arroz Carreteiro, Mandioca na Manteiga, Farofa de Castanha, Vinagrete).
   - Escolha de até 2 molhos opcionais (Chimichurri, Molho Lambão, Geleia de Pimenta Defumada).
   - Campo de observações para a cozinha.
   - Controle de quantidade (- / +) e botão "Adicionar à Comanda".

3. **Comanda da Mesa 14 & Pagamento (BillScreen)**:
   - Itens consumidos na comanda com status de entrega e ajuste de quantidade.
   - Divisão de conta de 1 a 6 pessoas com cálculo instantâneo por pessoa.
   - Seleção de gorjeta do garçom (0%, 10%, 12% Padrão com repasse integral à equipe).
   - Resumo financeiro completo (Subtotal, Taxa, Desconto PIX 5%, Total).
   - Opções de pagamento (PIX Instantâneo com cópia de chave, Cartão de Crédito e Carteira Digital).
   - Botão "Pagar & Ativar Câmera" com liberação imediata do 8º selo.

4. **Estúdio Social da Mesa (CameraSocialScreen)**:
   - Viewfinder interativo com visualização da foto da mesa.
   - Seleção de molduras temáticas ("Fogo & Brasa", "Vintage Steak", "Noite Épica", "8º Selo Pitmaster").
   - Botão de disparo com flash visual.
   - Salvar foto e enviar ao Mural das 8 Mesas.

5. **Passaporte Pitmaster (PassportScreen)**:
   - Cartela com os 8 selos comemorativos com datas e números de mesa.
   - Voucher desbloqueado com código de cupom `#SEMBANHA-8SELOS-2025` e botão de cópia.
   - Ação para resgatar brinde presencialmente com o garçom.
   - Galeria do Mural das 8 Mesas com as fotos registradas pelos clientes.
