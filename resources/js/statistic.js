function randomScalingFactor() {
	return Math.floor(Math.random() * 100);
}

window.chartColors = {
	red: "rgb(255, 99, 132)",
	orange: "rgb(255, 159, 64)",
	yellow: "rgb(255, 205, 86)",
	green: "rgb(75, 192, 192)",
	blue: "rgb(54, 162, 235)",
	purple: "rgb(153, 102, 255)",
	grey: "rgb(201, 203, 207)",
};

var Location = [];
var CountbyLocation = [];
var DayOfWeek = [];
var CountbyDayOfWeek = [];

var chart1 = document.getElementById("chart1");
var chart2 = document.getElementById("chart2");

window.addEventListener("load", function () {
	drawChart();
});

function getStatisticData() {
	const xhr = getXMLHTTPRequest();

	xhr.open("POST", "getStatistic", false);
	xhr.onreadystatechange = function () {
		if (xhr.readyState == 4 && xhr.status == 200) {
			var result = xhr.responseText;
			result = result.trim();
			var resultArray = result.split("/");
			var list1 = JSON.parse(resultArray[0]);
			var list2 = JSON.parse(resultArray[1]);

			for (i in list1) {
				Location.push(list1[i].Location);
				CountbyLocation.push(list1[i].Count);
			}
			for (i in list2) {
				DayOfWeek.push(list2[i].DayOfWeek);
				CountbyDayOfWeek.push(list2[i].Count);
			}
		}
	};
	xhr.send();
}

function getXMLHTTPRequest() {
	let request = false;

	try {
		request = new XMLHttpRequest();
	} catch (err01) {
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (err02) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (err03) {
				request = false;
			}
		}
	}

	return request;
}

function drawChart() {
	getStatisticData();
	var myChart1 = new Chart(chart1, {
		type: "pie",
		data: {
			datasets: [
				{
					data: CountbyLocation,
					backgroundColor: [window.chartColors.red, window.chartColors.blue],
				},
			],
			labels: Location,
		},
		options: {
			responsive: false,
			title: {
				display: true,
				text: "장소별",
				fontSize: 24,
			},
			legend: {
				display: true,
				position: "right",
				labels : {
					fontSize: 16,
				}
			},
			tooltips: {
				mode: "index",
				intersect: true,
			},
		},
	});
	var myChart2 = new Chart(chart2, {
		type: "bar",
		data: {
			datasets: [
				{
					data: CountbyDayOfWeek,
					backgroundColor: [
						window.chartColors.red,
						window.chartColors.orange,
						window.chartColors.yellow,
						window.chartColors.green,
						window.chartColors.blue,
						window.chartColors.purple,
						window.chartColors.grey,
					],
				},
			],
			labels: DayOfWeek,
		},
		options: {
			responsive: false,
			title: {
				display: true,
				text: "요일별",
				fontSize: 24,
			},
			legend: {
				display: false,
			},
			tooltips: {
				mode: "index",
				intersect: true,
			},
			scales: {
				xAxes: [
					{
						ticks: {
							fontSize: 16,
						},
					},
				],
				yAxes: [
					{
						ticks: {
							beginAtZero: true,
							fontSize: 16,
						},
					},
				],
			},
		},
	});
}
