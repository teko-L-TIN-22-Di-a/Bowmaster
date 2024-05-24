import pygame
from bow import Bow
from examplecode.display import Display
pygame.init

display_size = pygame.Vector2(600, 500)
display = Display(display_size)

base_screen = pygame.Surface(display_size.xy)
base_screen.fill("green")

bow_base = pygame.Vector2(display_size.x/2,display_size.y-60)
bow = Bow(bow_base)

running = True

while running:

    for event in pygame.event.get():

        if event.type == pygame.QUIT:
            running = False

    image = bow.update()
    rect = image.get_rect()
    display.update([(base_screen, pygame.Vector2(0, 0)), (image, bow.draw_pos)])
    pygame.draw.line(display.surface, "red", bow.base, bow.aimpoint)
    pygame.draw.line(display.surface, "blue", bow.string_left, bow.string_base)
    pygame.draw.line(display.surface, "blue", bow.string_right, bow.string_base)
    pygame.display.flip()