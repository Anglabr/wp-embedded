const ctx = document.getElementById('chart').getContext('2d');

let dataEntryDiv = document.getElementsByName("data");

let turnedOn = []
let dates = []

let temp;
let n
if (dataEntryDiv.length >= 20){
    n = 20
}
else {
    n = dataEntryDiv.length
}

for ( let i = 0; i < n; i++) {
    temp = dataEntryDiv[dataEntryDiv.length - n + i].innerText.split(" ")
    dates[i] = temp[0]

    if (temp[1] === 'true'){
        turnedOn[i] =  100
    }
    else{
        turnedOn[i] = -100
    }
}

const dataChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: dates,
        datasets: [
            {
                label: 'Light Bulb usage chart',
                data: turnedOn,
                backgroundColor: 'rgba(255, 99, 132, 0.6)',
                borderWidth: 2,
                borderRadius: Number.MAX_VALUE,
            },

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

