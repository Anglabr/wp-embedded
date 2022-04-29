const temperatureCtx = document.getElementById('temperatureChart').getContext('2d');
const humidityCtx = document.getElementById('humidityChart').getContext('2d');
const soilMoistureCtx = document.getElementById('soilMoistureChart').getContext('2d');


let dataEntryDiv = document.getElementsByName("data");

let temperature = []
let humidity = []
let soilMoisture = []
let dates = []

let temp;
for (let i = 0; i < dataEntryDiv.length; i++) {
    temp = dataEntryDiv[i].innerText.split(" ")
    dates[i] = temp[0]
    temperature[i] = parseInt(temp[1])
    humidity[i] = parseInt(temp[2])
    soilMoisture[i] = parseInt(temp[3])
}

const temperatureChart = new Chart(temperatureCtx, {
    type: 'bar',
    data: {
        labels: dates,
        datasets: [{
            label: 'Temperature chart',
            data: temperature,
            backgroundColor: "red",
            borderColor: [],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

const humidityChart = new Chart(humidityCtx, {
    type: 'bar',
    data: {
        labels: dates,
        datasets: [{
            label: 'Humidity chart',
            data: humidity,
            backgroundColor: "blue",
            borderColor: [],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

const soilMoistureChart = new Chart(soilMoistureCtx, {
    type: 'bar',
    data: {
        labels: dates,
        datasets: [{
            label: 'Soil moisture chart',
            data: soilMoisture,
            backgroundColor: "orange",
            borderColor: [],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

