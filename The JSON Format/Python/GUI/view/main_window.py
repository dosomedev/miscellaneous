import tkinter as tk
from data.api_access import ApiAccess
from model.animal import Animal

class MainWindow:
    def __init__(self):
        # Create main window.
        self.window = tk.Tk()
        self.window.title("Animal Info")

        self.init_fields()
        self.setup_layout()
        
    def init_fields(self):
        # Create fields.
        self.lbl_index = tk.Label(self.window, text="Index:")
        self.txt_index = tk.Entry(self.window, width=15)
        self.btn_show = tk.Button(self.window, text="Show Animal Info", command=self.btn_show_clicked)

        self.lbl_animal = tk.Label(self.window, text="Animal:")
        self.txt_animal = tk.Entry(self.window, width=30)

        self.lbl_name = tk.Label(self.window, text="Name:")
        self.txt_name = tk.Entry(self.window, width=30)

        self.lbl_breed = tk.Label(self.window, text="Breed:")
        self.txt_breed = tk.Entry(self.window, width=30)

        self.lbl_age = tk.Label(self.window, text="Age:")
        self.txt_age = tk.Entry(self.window, width=30)

        self.set_fields_readonly()

    def setup_layout(self):
        # Set layout.
        self.lbl_index.grid(row=0, column=0, padx=3, pady=3)
        self.txt_index.grid(row=0, column=1, padx=3, pady=3)
        self.btn_show.grid(row=0, column=2, padx=3, pady=3)

        self.lbl_animal.grid(row=1, column=0, padx=3, pady=3)
        self.txt_animal.grid(row=1, column=1, columnspan=2, padx=3, pady=3)

        self.lbl_name.grid(row=2, column=0, padx=3, pady=3)
        self.txt_name.grid(row=2, column=1, columnspan=2, padx=3, pady=3)

        self.lbl_breed.grid(row=3, column=0, padx=3, pady=3)
        self.txt_breed.grid(row=3, column=1, columnspan=2, padx=3, pady=3)

        self.lbl_age.grid(row=4, column=0, padx=3, pady=3)
        self.txt_age.grid(row=4, column=1, columnspan=2, padx=3, pady=3)

    def set_visible(self):
        self.window.mainloop()

    def set_fields_readonly(self):
        self.txt_animal.configure(state="readonly")
        self.txt_name.configure(state="readonly")
        self.txt_breed.configure(state="readonly")
        self.txt_age.configure(state="readonly")

    def set_fields_normal(self):
        self.txt_animal.configure(state="normal")
        self.txt_name.configure(state="normal")
        self.txt_breed.configure(state="normal")
        self.txt_age.configure(state="normal")

    def set_fields(self, animal: Animal):
        self.set_fields_normal()
        
        self.txt_animal.delete(0, tk.END)
        self.txt_name.delete(0, tk.END)
        self.txt_breed.delete(0, tk.END)
        self.txt_age.delete(0, tk.END)

        if animal is not None:
            self.txt_animal.insert(0, animal.animal_type)
            self.txt_name.insert(0, animal.name)
            self.txt_breed.insert(0, animal.breed)
            self.txt_age.insert(0, animal.age)

        self.set_fields_readonly()

    def btn_show_clicked(self):
        # Get index input.
        txt_index_value = self.txt_index.get()
        if txt_index_value.isdigit():
            # Convert index input to int.
            index_number = int(txt_index_value)

            # Get animals from web service.
            api_access = ApiAccess()
            animals = api_access.get_animals()

            # Check if index is valid.
            if 0 <= index_number < len(animals):
                print("Setting fields.")
                self.set_fields(animals[index_number])
            else:
                self.set_fields(None)
                print("Invalid index. Please enter an existing index.")

        else:
            self.set_fields(None)
            print("Invalid input. Please enter a number.")
