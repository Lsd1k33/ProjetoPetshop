var selectedRow = null;

function onFormSubmit() {
    var formData = readFormData();
    insertNewRecord(formData);
  }
  
  function readFormData(){
    var formData = {};
    formData["procedimento"] =      document.getElementById("procedimento").value;
    formData["observação"] =    document.getElementById("observação").value;
    formData["nomeCliente"] = document.getElementById("nomeCliente").value;
    formData["nomeAnimal"] =   document.getElementById("nomeAnimal").value;
    formData["marcData"] =  document.getElementById("marcData").value;
    formData["marcTime"] =       document.getElementById("marcTime").value;
    return formData;
  }
  
  function insertNewRecord(data) {
    var table = document.getElementById("listaPasseio").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.observação;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.nomeCliente;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.nomeAnimal;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = data.marcData;
    cell5 = newRow.insertCell(4);
    cell5.innerHTML = data.marcTime;
    cell6 = newRow.insertCell(5);
    cell6.innerHTML = `<a onClick= "onEdit(this)">Edit</a>
                       <a>Delete</a>`; //'' "" ``
  }

  function resetForm(){
    document.getElementById("procedimento").value = "";
    document.getElementById("observação").value = "";
    document.getElementById("nomeCliente").value = "";
    document.getElementById("nomeAnimal").value = "";
    document.getElementById("marcData").value = "";
    document.getElementById("marcTime").value = "";
  }

  function onEdit (td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("procedimento").value = selectedRow.cells[0].innerHTML;
    document.getElementById("observação").value = selectedRow.cells[1].innerHTML;
    document.getElementById("nomeCliente").value = selectedRow.cells[2].innerHTML;
    document.getElementById("nomeAnimal").value = selectedRow.cells[3].innerHTML;
    document.getElementById("marcData").value = selectedRow.cells[4].innerHTML;
    document.getElementById("marcTime").value = selectedRow.cells[5].innerHTML;
  }