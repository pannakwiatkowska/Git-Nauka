from flask import Flask
app = Flask(__name__)

@app.route("/")
def index():
    return "Witaj użytkowniku!"

@app.route("/hello/<imie>")
def hello(imie):
    #return str(imie)    
    return "Hello " + imie + "!"

if __name__ == "__main__":
    app.run()

