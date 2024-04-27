import pygame

class Bow:

    base: pygame.Vector2
    aimpoint: pygame.Vector2

    def __init__(self, base: pygame.Vector2) -> None:
        self.base = base

    def update(self, point: pygame.Vector2):
        self.aimpoint = point