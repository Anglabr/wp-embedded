class Stream{
    constructor(endpoint) {
        this.endpoint = endpoint
        this.eventSource = null
    }
    init(handleEvent) {
        this.eventSource = new EventSource(this.endpoint)
        this.eventSource.addEventListener("message", handleEvent)
        this.eventSource.addEventListener("data", handleEvent)
        this.eventSource.onerror = () =>{
            console.log("Error occurred")
            this.close()
        }
    }

    close() {
        this.eventSource.close()
    }
}

const lightBulbStream = new Stream("/lightbulb/stream")


const handleLightBulbToggleStream = (event) => {

    console.log(event.data)

    const text = event.data
    if (text !== ""){
        const id = text.split(" ")[0]
        document.getElementById(id).innerText = text.split(" ")[1];
    }

}

window.onload = () => {
    lightBulbStream.init(handleLightBulbToggleStream)
}

window.onbeforeunload = () => {
    lightBulbStream.close()
}

