<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Application Frontend (Angular)</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
</head>
<body>

  <h1>Application Frontend (Angular)</h1>

  <p>Ce document présente de manière détaillée l’implémentation de l’application cliente développée avec <strong>Angular</strong>. Cette application constitue la couche frontend du projet full-stack dédié à la gestion des produits et assure l’interaction entre l’utilisateur et le backend basé sur <strong>Spring Boot</strong>.</p>

  <h2>1. Rôle de l’application</h2>
  <p>L’application Angular a pour objectif de fournir une interface utilisateur ergonomique permettant l’interaction avec le système de gestion des produits. Elle permet principalement la consultation de la liste des produits ainsi que leur suppression.</p>
  <p>L’ensemble des données manipulées par l’application est obtenu et modifié exclusivement via des appels à une <strong>API REST</strong> exposée par le backend Spring Boot, garantissant ainsi une séparation claire entre la logique métier et la présentation.</p>

  <h2>2. Technologies utilisées</h2>
  <ul>
      <li><strong>Framework :</strong> Angular</li>
      <li><strong>Communication HTTP :</strong> <code>HttpClientModule</code> pour l’échange de données avec l’API REST</li>
      <li><strong>Interface utilisateur :</strong> Bootstrap et Bootstrap Icons</li>
      <li><strong>Gestion des dépendances :</strong> npm</li>
  </ul>

  <h2>3. Détails de l’implémentation</h2>
  <p>L’architecture de l’application frontend repose sur une approche modulaire, basée sur la séparation entre <strong>composants</strong> et <strong>services</strong>, afin d’améliorer la maintenabilité et la lisibilité du code.</p>

  <h3>Service de données (<code>ProductService.ts</code>)</h3>
  <p>Le service <code>ProductService</code> assure la centralisation de la communication avec l’API backend. Il encapsule les appels HTTP nécessaires aux différentes opérations (récupération et suppression des produits), ce qui permet de découpler les composants de la logique d’accès aux données.</p>
  <pre><code class="typescript">
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

  <h3>Composant d’affichage (<code>products.ts</code>)</h3>
  <p>Le composant <code>Products</code> constitue le composant principal chargé de l’affichage et de la gestion des produits. Il s’appuie sur le <code>ProductService</code> pour récupérer les données depuis le backend et pour traiter les actions effectuées par l’utilisateur, notamment la suppression d’un produit.</p>
  <pre><code class="typescript">
@Component({ /* ... */ })
export class Products implements OnInit {
  products: any = [];

  constructor(
    private productService: ProductService,
    private cd: ChangeDetectorRef
  ) {}

  ngOnInit() {
    this.getAllProducts();
  }

  getAllProducts() {
    this.productService.getAllProducts().subscribe({
      next: resp => {
        this.products = resp;
        this.cd.detectChanges(); // Forcer la mise à jour de la vue
      },
      error: err => console.log(err)
    });
  }

  handleDelete(p: any) {
    if (confirm("Are you sure you want to delete this product?")) {
      this.productService.deleteProduct(p).subscribe({
        next: () => this.getAllProducts(),
        error: err => console.log(err)
      });
    }
  }
}
    </code></pre>

  <h3>Template d’affichage (<code>products.html</code>)</h3>
  <pre><code class="html">
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
          <td>{{ p.id }}</td>
          <td>{{ p.name }}</td>
          <td>{{ p.price }}</td>
          <td>
            @if (p.selected) { <i class="bi bi-check-circle"></i> }
            @else { <i class="bi bi-circle"></i> }
          </td>
          <td>
            <button (click)="handleDelete(p)" class="btn btn-outline-danger">
              <i class="bi bi-trash"></i>
            </button>
          </td>
        </tr>
      }
    </tbody>
  }
</table>
    </code></pre>

  <h2>4. Mise en œuvre de l’application</h2>
  <ol>
      <li><strong>Installation des dépendances :</strong>
          <pre><code>npm install</code></pre>
      </li>
      <li><strong>Lancement du serveur de développement :</strong>
          <pre><code>ng serve</code></pre>
      </li>
      <li>Accéder à l’application via un navigateur web à l’adresse : <code>http://localhost:4200/</code></li>
  </ol>

  <div class="note">
      <strong>Remarque :</strong> Le serveur backend Spring Boot doit être en cours d’exécution sur <code>http://localhost:8080</code> afin de permettre le bon fonctionnement de l’application frontend.
  </div>

</body>
</html>
