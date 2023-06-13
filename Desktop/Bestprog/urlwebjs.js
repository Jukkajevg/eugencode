function shortenUrl() {
  const url = document.getElementById("url-input").value;
  if (url) {
    fetch("/shorten", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ url })
    })
      .then(response => response.json())
      .then(data => {
        document.getElementById("result-container").style.display = "block";
        document.getElementById("result").innerText = data.shortUrl;
      })
      .catch(error => console.error(error));
  }
}

function lengthenUrl() {
  const url = document.getElementById("url-input").value;
  if (url) {
    fetch("/lengthen", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({ url })
    })
      .then(response => response.json())
      .then(data => {
        document.getElementById("result-container").style.display = "block";
        document.getElementById("result").innerText = data.longUrl;
      })
      .catch(error => console.error(error));
  }
}

