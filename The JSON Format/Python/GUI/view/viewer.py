from view.main_window import MainWindow

class Viewer:
    def __init__(self):
        self.main_window = MainWindow()

    def set_visible(self):
        self.main_window.set_visible()
