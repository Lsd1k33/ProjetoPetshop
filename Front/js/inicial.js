// Troca o título
function myFunction2() {
  document.getElementById("demo").innerHTML = "This is a heading";
}

//Troca o backgrond do body
function trocaBackColor() {
  document.getElementById("bodyID").classList = "bodyClass2";
  alert("Cor de fundo trocada!");
}

//Altera a saudação da página
function escreveSaudacao(nome) {
    document.getElementById("saudacao").innerHTML = "Boa noite " + nome +"!";
}
