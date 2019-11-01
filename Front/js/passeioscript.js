var selectedRow = null;

function onFormSubmit() {
    var formData = readFormData();
    insertNewRecord(formData);
    if (selecetedRow == null)
      insertNewRecord(formData);
      else
      updateRecord(formData);
      resetForm();
  }
  
  function readFormData(){
    var formData = {};
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
                       <a onClick= "onDelete(this)">Delete</a>`; //'' "" ``
  }

  function resetForm(){
    document.getElementById("observação").value = "";
    document.getElementById("nomeCliente").value = "";
    document.getElementById("nomeAnimal").value = "";
    document.getElementById("marcData").value = "";
    document.getElementById("marcTime").value = "";
    selectedRow = null;
  }

  function onEdit (td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("observação").value = selectedRow.cells[0].innerHTML;
    document.getElementById("nomeCliente").value = selectedRow.cells[1].innerHTML;
    document.getElementById("nomeAnimal").value = selectedRow.cells[2].innerHTML;
    document.getElementById("marcData").value = selectedRow.cells[3].innerHTML;
    document.getElementById("marcTime").value = selectedRow.cells[4].innerHTML;
  }

  function updateRecord(){
    selectedRow.cells[0].innerHTML = formData.observação;
    selectedRow.cells[1].innerHTML = formData.nomeCliente;
    selectedRow.cells[2].innerHTML = formData.nomeAnimal;
    selectedRow.cells[3].innerHTML = formData.marcData;
    selectedRow.cells[4].innerHTML = formData.marcTime;
  }

  function onDelete(td) {
    if (confirm('Tem certeza que quer apagar?')) {
        row = td.parentElement.parentElement;
        document.getElementById("listaPasseio").deleteRow(row.rowIndex);
        resetForm();
    }
  }