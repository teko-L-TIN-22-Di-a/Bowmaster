import pygame
from bow import Bow
from display import Display
pygame.init

display_size = pygame.Vector2(600, 500)
display = Display(display_size)

base_screen = pygame.Surface(display_size.xy)
base_screen.fill("green")

bow_base = pygame.Vector2(display_size.x/2,display_size.y-10)
bow = Bow(bow_base)

running = True

while running:

    for event in pygame.event.get():

        if event.type == pygame.QUIT:
            running = False

    display.update(base_screen)
    pygame.display.flip()