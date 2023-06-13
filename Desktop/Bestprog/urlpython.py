from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/shorten", methods=["POST"])
def shorten():
    data = request.get_json()
    url = data["url"]
    # Your URL shortening logic here
    short_url = "https://example.com/shortened"
    return jsonify({"shortUrl": short_url})

@app.route("/lengthen", methods=["POST"])
def lengthen():
    data = request.get_json()
    url = data["url"]
    # Your URL lengthening logic here
    long_url = "https://example.com/long-url"
    return jsonify({"longUrl": long_url})

if __name__ == "__main__":
    app.run()
