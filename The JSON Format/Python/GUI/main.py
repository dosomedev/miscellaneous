from view.viewer import Viewer

class Main:
    def __init__(self):
        self.viewer = Viewer()
        self.viewer.set_visible()

# Run program.
if __name__ == "__main__":
    main = Main()
