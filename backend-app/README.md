<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Application Backend Spring Boot - README</title>
</head>
<body>

<header>
    <h1>Application Backend avec REST Controller (Spring Boot)</h1>
</header>

<main>
    <p>Ce document décrit de manière détaillée l’implémentation de l’API REST développée avec <strong>Spring Boot</strong>. Cette application représente la couche backend du projet full-stack de gestion de produits et assure le traitement de la logique métier ainsi que la persistance des données.</p>

<h2>1. Rôle de l’application</h2>
<p>Le backend a pour rôle principal d’exposer une <strong>API REST</strong> permettant la gestion complète de l’entité <code>Product</code>. Il constitue la source centrale des données pour l’application frontend développée avec Angular et prend en charge l’ensemble des opérations CRUD (Create, Read, Update, Delete). Cette approche garantit une séparation claire entre la couche de présentation et la logique métier, tout en assurant la cohérence et la sécurité des données.</p>

<h2>2. Technologies utilisées</h2>
<ul>
    <li><strong>Framework :</strong> Spring Boot</li>
    <li><strong>Accès aux données :</strong> Spring Data JPA</li>
    <li><strong>Développement de l’API REST :</strong> Spring Web</li>
    <li><strong>Base de données :</strong> H2 (base en mémoire)</li>
    <li><strong>Utilitaires :</strong> Lombok (réduction du code répétitif)</li>
    <li><strong>Outil de build :</strong> Maven</li>
</ul>

<h2>3. Détails de l’implémentation</h2>
<p>L’application adopte une architecture en couches classique, favorisant la clarté, la maintenabilité et l’évolutivité du code. Cette architecture est composée principalement des couches <strong>Repository</strong>, <strong>Service</strong> et <strong>Controller</strong>.</p>

<h3>Couche de persistance (JPA)</h3>
<p><strong>Entité <code>Product.java</code></strong> : modélise un produit dans la base de données.</p>
<pre><code>@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
@NotEmpty
private String name;
@Min(0)
private double price;
private Boolean selected;
}</code></pre>

    <p><strong>Repository <code>ProductRepository.java</code></strong> : étend <code>JpaRepository</code> pour bénéficier des méthodes CRUD automatiquement.</p>
    <pre><code>public interface ProductRepository extends JpaRepository<Product,Long> {
}</code></pre>

<h3>Couche web – API REST</h3>
<p><strong>Contrôleur <code>ProductRestAPI.java</code></strong> : expose les endpoints REST pour interagir avec les ressources <code>Product</code>. L’annotation <code>@CrossOrigin("*")</code> autorise les requêtes provenant de différentes origines.</p>
<pre><code>@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@AllArgsConstructor
public class ProductRestAPI {
private ProductService productService;

@GetMapping("/products")
public List&lt;Product&gt; getAllProducts(){
    return productService.findAll();
}

@DeleteMapping("/products/{id}")
public void deleteProductById(@PathVariable Long id){
    productService.deleteById(id);
}
}</code></pre>

<h3>Initialisation des données</h3>
<pre><code>@Bean
CommandLineRunner init(ProductService productService) {
return args -&gt; {
    productService.addProduct(new Product(null, "ordinateur", 5000, true));
    productService.addProduct(new Product(null, "telephone", 2500, true));
};
}</code></pre>

<h2>4. Configuration de la base de données</h2>
<p>Le backend utilise une base de données <strong>H2 en mémoire</strong>, configurée dans <code>application.properties</code>. Ce choix permet de travailler sans dépendre d’un SGBD externe.</p>
<pre><code>spring.datasource.url=jdbc:h2:mem:product-db
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create</code></pre>
<p>La console H2 est accessible ici : <a href="http://localhost:8080/h2-console" target="_blank">http://localhost:8080/h2-console</a></p>

<h2>5. Mise en œuvre de l’application</h2>
<ul>
    <li><strong>Via IDE :</strong> exécuter la méthode <code>main</code> de la classe <code>BackendAppApplication</code>.</li>
    <li><strong>Via Maven :</strong>
        <pre><code>mvn spring-boot:run</code></pre>
    </li>
</ul>
<p>Une fois l’application démarrée, l’API REST est accessible à l’adresse : <code>http://localhost:8080</code></p>
</main>

<footer>
    &copy; 2026 Projet Full-Stack Angular & Spring Boot
</footer>

</body>
</html>
