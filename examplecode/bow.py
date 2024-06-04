import pygame
import math

class Bow:

    base: pygame.Vector2
    aimpoint: pygame.Vector2
    surface: pygame.Surface
    aimpoint: pygame.Vector2

    def __init__(self, base: pygame.Vector2) -> None:
        self.base = base
        self.surface = pygame.image.load("examplecode/Bow1.png")
        self.aimpoint = pygame.Vector2(pygame.mouse.get_pos())
        self.rect = self.surface.get_rect()
        self.size = pygame.Vector2(self.rect.size)
        self.string_base = pygame.Vector2(base.x, base.y + self.rect.centery)
        self.draw_pos = self.base - pygame.Vector2(self.rect.centerx, self.rect.top)
        self.string_left = pygame.Vector2(self.rect.bottomleft),
        self.string_right = pygame.Vector2(self.rect.bottomright)


    def update(self):
        aim_direction = self.get_mouse_info()/(2*math.pi)*360
        rotated_image = pygame.transform.rotate(self.surface, aim_direction)
        rect_offset = pygame.Vector2(rotated_image.get_rect().center)
        self.draw_pos = self.base - rect_offset
        self.update_string()
        return rotated_image
    
    def get_mouse_info(self) -> int:
        self.aimpoint = pygame.Vector2(pygame.mouse.get_pos())
        diff_x = (self.aimpoint.x-self.base.x)
        diff_y = (self.aimpoint.y-self.base.y)
        if diff_y >= 0:
            diff_y = -1
        aim_direction = math.atan(diff_x/diff_y)
        return aim_direction
    
    def update_string(self):
        direction = self.get_mouse_info()
        diff_x = math.cos(-direction)*self.rect.centerx
        diff_y = math.sin(-direction)*self.rect.centerx
        diff_base_x = math.sin(-direction)*self.rect.centery
        diff_base_y = math.cos(-direction)*self.rect.centery
        diff = pygame.Vector2(diff_x, diff_y)
        diff_base = pygame.Vector2(-diff_base_x, diff_base_y)
        self.string_base = self.base + diff_base
        self.string_left = self.string_base + diff
        self.string_right = self.string_base - diff