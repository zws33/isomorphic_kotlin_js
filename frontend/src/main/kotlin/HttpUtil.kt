import org.w3c.xhr.XMLHttpRequest

fun xhrGet(url: String, callback: (String) -> Unit) {
    val request = XMLHttpRequest()
    request.open("GET", url)
    request.onload = {
        if (request.readyState == 4.toShort() && request.status == 200.toShort()) {
            callback(request.responseText)
        }
    }
    request.send()
}