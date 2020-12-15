var selUserList = [];
var selectedUsers = document.getElementById("selectedUsers");

function select(e) {
	user = e.getElementsByTagName("td")[2].textContent;
	if (!e.classList.contains('selected')) {
		e.classList.add('selected');
		selUserList.push(user);
	} else {
		e.classList.remove('selected');
		const idx = selUserList.indexOf(user);
		if (idx > -1) selUserList.splice(idx, 1);
	}
	selectedUsers.value = selUserList.join(",");
}

function sortTable(n) {
	var table,
		rows,
		switching,
		i,
		x,
		y,
		shouldSwitch,
		dir,
		switchcount = 0;

	switching = true;
	dir = "asc";

	while (switching) {
		switching = false;
		table = document.getElementsByTagName("table")[0];
		rows = table.rows;

		for (i = 1; i < rows.length - 1; i++) {
			shouldSwitch = false;
			x = rows[i].getElementsByTagName("td")[n];
			y = rows[i + 1].getElementsByTagName("td")[n];

			if (dir == "asc") {
				if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					break;
				}
			} else if (dir == "desc") {
				if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
					shouldSwitch = true;
					break;
				}
			}
		}

		if (shouldSwitch) {
			rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
			switching = true;
			switchcount++;
		} else {
			if (switchcount == 0 && dir == "asc") {
				dir = "desc";
				switching = true;
			}
		}
	}
}
