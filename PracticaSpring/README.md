##Practica crud con springboot-Swagger- Junit

En esta aprte se crearan los controladores de CRUD:

Para guardar de la manera mas basica dentro del controller
se usa el codigo:

`@PostMapping("/laptops")
    public Laptop create(@RequestBody Laptop laptop){
    return laptopRepository.save(laptop);
}
`