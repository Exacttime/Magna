
# Diagrama da classe Usuario

```mermaid
classDiagram
    class Usuario {
        -Long id
        -String username
        -String password
        -List<Manga> mangas
    }
    class Manga {
        -Long id
        -String titulo
        -String autor
    }

    Usuario --> Manga
