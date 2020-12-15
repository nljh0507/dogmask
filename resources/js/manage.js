var selUserList = document.getElementById("selectedUsers").value.split(",");
var oldID = document.getElementById("oldID");

function inputOriginalData() {
	var mod = document.getElementById("mod");
	var name = mod.getElementsByClassName("name")[0];
	var authority = mod.getElementsByClassName("authority")[0];
	var description = mod.getElementsByClassName("description")[0];
	var selected = document.getElementsByClassName("selected")[0];

	name.value = selected.getElementsByTagName("td")[2].textContent;
	oldID.value = name.value;
	authority.selectedIndex = 1; // TODO: -> selection
	description.value = selected.getElementsByTagName("td")[4].textContent;
}
