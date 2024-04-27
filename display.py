import pygame

class Display:

    size: pygame.Vector2

    def __init__(self, size: pygame.Vector2) -> None:
        self.size = size
        self.surface = pygame.display.set_mode(size.xy)
        pygame.display.set_caption('Bowmaster')

    def update(self, image: pygame.Surface):
        self.surface.blit(image, (0,0))