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
                backgroundColor: 'rgba(255, 99, 132, 0.6)',
                borderWidth: 2,
                borderRadius: Number.MAX_VALUE,
            },

            {
                label: 'Humidity chart',
                data: humidity,
                backgroundColor: 'rgba(54, 162, 235, 0.6)',
                borderWidth: 2,
                borderRadius: Number.MAX_VALUE,
            },

            {
                label: 'Soil moisture chart',
                data: soilMoisture,
                backgroundColor: 'rgba(255, 159, 64, 0.6)',
                borderWidth: 2,
                borderRadius: Number.MAX_VALUE,
            }

        ]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        },
        legend:{
            position: 'right',
        }
    }
});

