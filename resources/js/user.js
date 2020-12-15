let constraints = { video: { facingMode: "user" }, audio: false };
const cameraView = document.querySelector("#camera--view");
const cameraOutput = document.querySelector("#camera--output");
const cameraSensor = document.querySelector("#camera--sensor");
var capturedImage = document.querySelector("#capture");
const cameraTrigger = document.querySelector("#camera--trigger");
const guide = document.querySelector("#guide");
var state = document.getElementById("state");

// 페이지가 로드되면 함수 실행
window.addEventListener("load", function () {
	cameraStart();
	printClock();
});

function cameraStart() {
	navigator.mediaDevices
		.getUserMedia(constraints)
		.then(function (stream) {
			track = stream.getTracks()[0];
			cameraView.srcObject = stream;
		})
		.catch(function (error) {
			console.error("카메라에 문제가 있습니다.", error);
		});
}

// 촬영 버튼 클릭 리스너
cameraTrigger.addEventListener("click", function () {
	cameraSensor.width = cameraView.videoWidth;
	cameraSensor.height = cameraView.videoHeight;
	cameraSensor.getContext("2d").drawImage(cameraView, 0, 0);
	var dataURL = cameraSensor.toDataURL("image/jpeg", 1.0);

	capturedImage = cameraSensor;

	// Upload to Server
	const xhr = getXMLHTTPRequest();

	guide.style.visibility = "hidden";
	xhr.open("POST", "process", true);
	cameraTrigger.innerText = "분석 중..."; // TODO: animation
	xhr.onreadystatechange = function () {
		cameraTrigger.innerText = "사진 촬영";

		if (xhr.readyState == 4 && xhr.status == 200) {
			capturedImage.style.visibility = "visible";
			const mark = document.getElementById("mark");
			const spec = document.getElementById("spec");
			var result = xhr.responseText;
			result = result.trim();
			var resultArray = result.split("/");
			var rate = Number(resultArray[0]); // 인식률
			var temp = Number(resultArray[1]); // 발열

			cameraTrigger.style.visibility = "hidden";
			state.style.visibility = "visible";

			if (rate < 80) {
				spec.style.fontSize = "28px"; // 임시
				spec.innerText = "입마개를 착용해 주세요";
				mark.src = "resources/images/fail_muzzle.svg";
				state.classList.add("fail");
			} else {
				spec.innerText = temp + "℃";
				if (temp <= 39.2) {
					state.classList.add("pass");
					mark.src = "resources/images/pass.svg";
				} else {
					state.classList.add("fail");
					mark.src = "resources/images/fail_temp.svg";
				}
			}

			setTimeout(function () {
				spec.style.fontSize = "40px"; // 임시
				guide.style.visibility = "visible";
				capturedImage.style.visibility = "hidden";
				cameraTrigger.style.visibility = "visible";
				state.style.visibility = "hidden";
				state.classList.remove("pass");
				state.classList.remove("fail");
			}, 2000);
		}
	};
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("imageData=" + encodeURIComponent(dataURL));
});

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

function logoutMessage() {
	// TODO: confirm() -> modal window
	if (confirm("로그아웃하시겠습니까?")) {
		location.href = "loginUser";
	}
}

// 시계
function printClock() {
	var clock = document.getElementById("clock");
	var currentDate = new Date();
	var calendar = currentDate.getFullYear() + "년 " + (currentDate.getMonth() + 1) + "월 " + currentDate.getDate() + "일"; // 현재 날짜
	var amPm = "오전";
	var currentHours = addZeros(currentDate.getHours(), 2);
	var currentMinute = addZeros(currentDate.getMinutes(), 2);
	var currentSeconds = addZeros(currentDate.getSeconds(), 2);
	var currentTime = currentHours + ":" + currentMinute + ":" + currentSeconds;

	if (currentHours >= 12) {
		amPm = "오후";
		currentHours = addZeros(currentHours - 12, 2);
	}

	clock.innerHTML = calendar + " " + amPm + " " + currentTime; // 날짜, 시간 출력

	setTimeout("printClock()", 1000); // 1초마다 printClock() 호출
}

// 자릿수 맞춤 (ex. 5 -> 05)
function addZeros(num, digit) {
	var zero = "";
	num = num.toString();
	if (num.length < digit) {
		for (i = 0; i < digit - num.length; i++) {
			zero += "0";
		}
	}
	return zero + num;
}
