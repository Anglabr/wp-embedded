const ctx = document.getElementById('chart').getContext('2d');

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

const dataChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: dates,
        datasets: [
            {
                label: 'Temperature chart',
                data: temperature,
                backgroundColor: "red",
                borderColor: [],
                borderWidth: 1
            },

            {
                label: 'Humidity chart',
                data: humidity,
                backgroundColor: "blue",
                borderColor: [],
                borderWidth: 1
            },

            {
                label: 'Soil moisture chart',
                data: soilMoisture,
                backgroundColor: "orange",
                borderColor: [],
                borderWidth: 1
            }

        ]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});

