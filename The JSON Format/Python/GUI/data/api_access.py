import requests
from model.animal import Animal

class ApiAccess:
    _instance = None

    def __new__(cls):
        if cls._instance is None:
            cls._instance = super().__new__(cls)
        return cls._instance
    
    def __init__(self):
        self.api_uri = "http://localhost:8080/api"

    def get_animals(self):
        animals = []

        # Prepare API URL.
        url = self.api_uri + "/animals"
        try:
            self.response = requests.get(url)

            if self.response.status_code != 200:
                print("Failed to retrieve data from web service.")

            json_data = self.response.json()

            # Convert JSON to array of animals.
            for animal_data in json_data:
                # Extract relevant data.
                animal_type = animal_data.get("animal")
                name = animal_data.get("name")
                breed = animal_data.get("breed")
                age = animal_data.get("age")

                # Create an Animal object and append it to the animals list.
                animal = Animal(animal_type, name, breed, age)
                animals.append(animal)

        except requests.exceptions.RequestException:
            print("The web service could not be reached.")

        return animals
