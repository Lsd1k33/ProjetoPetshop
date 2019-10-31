function onFormSubmit() {
  var formData = readFormData();
  insertNewRecord(formData);
}

function readFormData(){
  var formData = {};
  formData["fullname"] = document.getElementById("fullname").value;
  formData["racaAnimal"] = document.getElementById("racaAnimal").value;
  formData["especieAnimal"] = document.getElementById("especieAnimal").value;
  formData["idadeAnimal"] = document.getElementById("idadeAnimal").value;
  formData["vacinaAnimal"] = document.getElementById("vacinaAnimal").value;
  formData["codDono"] = document.getElementById("codDono").value;
  return formData;
}

function insertNewRecord(data) {
  var table = document.getElementById("listaAnimal").getElementsByTagName('tbody')[0];
  var newRow = table.insertRow(table.length);
  cell1 = newRow.insertCell(0);
  cell1.innerHTML = data.fullname;
  cell2 = newRow.insertCell(1);
  cell2.innerHTML = data.racaAnimal;
  cell3 = newRow.insertCell(2);
  cell3.innerHTML = data.especieAnimal;
  cell4 = newRow.insertCell(3);
  cell4.innerHTML = data.racaAnimal;
  cell5 = newRow.insertCell(4);
  cell5.innerHTML = data.vacinaAnimal;
  cell6 = newRow.insertCell(5);
  cell6.innerHTML = data.codDono;
  cell7 = newRow.insertCell(6);
  cell7.innerHTML = `<a>Edit</a>
                    '<a>Delete</a>`; //'' "" ``
}