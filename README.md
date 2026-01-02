
<h1>Compte rendu du TP 4 : Développement d’une application Full-Stack Angular & Spring Boot</h1>

<p>
Ce document présente le rapport du quatrième travail pratique, consacré à la réalisation
d’une application web full-stack basée sur une architecture découplée. L’objectif principal
était de développer une interface utilisateur dynamique avec <strong>Angular</strong>, capable
d’interagir avec une <strong>API REST</strong> conçue en <strong>Spring Boot</strong>, dédiée à la gestion des produits.
</p>

<div class="section">
  <h2>1. Architecture générale de l’application</h2>

  <p>L’application repose sur une architecture client–serveur moderne :</p>

  <ul>
    <li>
      <strong>Backend (Serveur)</strong> : API REST développée avec <strong>Spring Boot</strong>, responsable
      de la logique métier, de la gestion des données et des opérations CRUD.
    </li>
    <li>
      <strong>Frontend (Client)</strong> : application monopage (SPA) développée avec <strong>Angular</strong>,
      consommant les services REST exposés par le backend.
    </li>
  </ul>

  <p>
    Cette séparation garantit une meilleure maintenabilité ainsi qu’un développement
    et un déploiement indépendants des deux parties.
  </p>
</div>

<div class="section">
  <h2>2. Technologies et outils utilisés</h2>

  <h3>Backend (<code>backend-app</code>)</h3>
  <ul>
    <li>Spring Boot</li>
    <li>Spring Data JPA</li>
    <li>Spring Web</li>
    <li>Base de données H2 (en mémoire)</li>
    <li>Lombok</li>
    <li>Maven</li>
  </ul>

  <h3>Frontend (<code>angular-app</code>)</h3>
  <ul>
    <li>Angular</li>
    <li>HttpClientModule</li>
    <li>Bootstrap & Bootstrap Icons</li>
    <li>npm</li>
  </ul>
</div>

<div class="section">
  <h2>3. Détails de l’implémentation</h2>

  <h3>Backend – Spring Boot</h3>
  <p>
    Le backend est structuré selon une architecture en couches :
    entités, repositories, services et contrôleurs.
  </p>

  <h4>Entité <code>Product.java</code></h4>
  <pre><code class="language-java">
@Entity
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @Min(0)
    private double price;
    private Boolean selected;
}
  </code></pre>

  <h4>Repository <code>ProductRepository.java</code></h4>
  <pre><code class="language-java">
public interface ProductRepository extends JpaRepository&lt;Product, Long&gt; {
}
  </code></pre>

  <h4>Contrôleur REST</h4>
  <pre><code class="language-java">
@RestController
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
}
  </code></pre>
</div>

<div class="section">
  <h2>4. Configuration de la base de données</h2>

  <p>
    Le backend utilise une base de données <strong>H2 en mémoire</strong>, facilitant les phases
    de développement et de test.
  </p>

  <pre><code class="language-properties">
spring.datasource.url=jdbc:h2:mem:product-db
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create
  </code></pre>

  <div class="note">
    La console H2 est accessible via : <code>http://localhost:8080/h2-console</code>
  </div>
</div>

<div class="section">
  <h2>5. Problèmes rencontrés et solutions</h2>

  <ul>
    <li>
      <strong>Problème CORS</strong> : blocage des requêtes entre Angular et Spring Boot.
      <br>
      <strong>Solution</strong> : ajout de l’annotation <code>@CrossOrigin("*")</code>.
    </li>
    <li>
      <strong>Rafraîchissement de la vue Angular</strong>.
      <br>
      <strong>Solution</strong> : utilisation de <code>ChangeDetectorRef</code>.
    </li>
  </ul>
</div>

<div class="section">
  <h2>6. Conclusion</h2>

  <p>
    Ce TP a permis de mettre en pratique les principes du développement full-stack moderne,
    notamment la création d’une API REST avec Spring Boot et le développement d’une interface
    utilisateur réactive avec Angular.
  </p>

  <p>
    Le découplage entre le frontend et le backend constitue une base solide pour la
    conception d’applications web évolutives.
  </p>
</div>
