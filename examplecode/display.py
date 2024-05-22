import pygame

class Display:

    size: pygame.Vector2

    def __init__(self, size: pygame.Vector2) -> None:
        self.size = size
        self.surface = pygame.display.set_mode(size.xy)
        pygame.display.set_caption('Bowmaster')

    def update(self, images: list[tuple[pygame.Surface, pygame.Vector2]]):
        self.surface.fill("green")
        for image in images:
            self.surface.blit(image[0], image[1])