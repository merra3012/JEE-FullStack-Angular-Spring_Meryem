<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Frontend Angular - Gestion de Produits</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body>
  <div class="container">

    <h1>Application Frontend – Angular</h1>

    <p>
      Ce document présente l’implémentation de l’application cliente développée avec
      <strong>Angular</strong>. Elle constitue la partie frontend du projet full-stack
      de gestion des produits.
    </p>

    <h2>1. Rôle de l’Application</h2>

    <p>
      L’application Angular assure l’interface utilisateur permettant d’interagir avec
      le système. Elle offre la possibilité de consulter la liste des produits et de
      les supprimer. Toutes les opérations s’effectuent via une API REST exposée par
      le backend Spring Boot.
    </p>

    <h2>2. Technologies Utilisées</h2>

    <ul>
      <li><strong>Framework :</strong> Angular</li>
      <li><strong>Communication HTTP :</strong> HttpClientModule</li>
      <li><strong>Interface utilisateur :</strong> Bootstrap & Bootstrap Icons</li>
      <li><strong>Gestion des dépendances :</strong> npm</li>
    </ul>

    <h2>3. Détails de l’Implémentation</h2>

    <p>
      Le frontend adopte une architecture modulaire basée sur la séparation
      entre composants et services afin de garantir une meilleure maintenabilité.
    </p>

    <h3>Service de Données – <code>ProductService.ts</code></h3>

    <p>
      Ce service regroupe tous les appels HTTP vers l’API backend et isole
      la logique d’accès aux données des composants.
    </p>

    <pre><code>
@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  getAllProducts() {
    return this.http.get("http://localhost:8080/api/products");
  }

  deleteProduct(p: any) {
    return this.http.delete("http://localhost:8080/api/products/" + p.id);
  }
}
    </code></pre>

    <h3>Composant d’Affichage – <code>products.ts</code></h3>

    <p>
      Ce composant permet l’affichage dynamique des produits et la gestion
      des actions utilisateur telles que la suppression.
    </p>

    <pre><code>
@Component({ /* ... */ })
export class Products implements OnInit {
  products: any = [];

  constructor(
    private productService: ProductService,
    private cd: ChangeDetectorRef,
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe({
      next: resp => {
        this.products = resp;
        this.cd.detectChanges();
      }
    });
  }

  handleDelete(p: any) {
    if (confirm("Are you sure you want to delete this product?")) {
      this.productService.deleteProduct(p).subscribe({
        next: () => this.getAllProducts()
      });
    }
  }
}
    </code></pre>

    <h3>Template – <code>products.html</code></h3>

    <p>
      La vue exploite la syntaxe moderne d’Angular pour afficher efficacement
      la liste des produits.
    </p>

    <pre><code>
<table class="table">
  <thead>
    <tr>
      <th>Id</th>
      <th>Name</th>
      <th>Price</th>
      <th>Selected</th>
    </tr>
  </thead>
  @if (products) {
    <tbody>
      @for (p of products; track p) {
        <tr>
          <td>{{p.id}}</td>
          <td>{{p.name}}</td>
          <td>{{p.price}}</td>
          <td>
            @if (p.selected) { ✔ }
            @else { ○ }
          </td>
        </tr>
      }
    </tbody>
  }
</table>
    </code></pre>

    <h2>4. Lancement de l’Application</h2>

    <ul>
      <li><code>npm install</code> – installation des dépendances</li>
      <li><code>ng serve</code> – démarrage du serveur de développement</li>
      <li>Accès via : <code>http://localhost:4200</code></li>
    </ul>

    <div class="note">
      <strong>Remarque :</strong> le backend Spring Boot doit être lancé
      sur <code>http://localhost:8080</code> afin de permettre la communication
      avec l’API REST.
    </div>

    <footer>
      Projet Full-Stack Angular & Spring Boot – Documentation Frontend
    </footer>

  </div>
</body>
</html>
