#### 🚗 Java’da Composition (Bileşim) ile Nesnelerin Gerçek Hayattaki İlişkisi ⚙️

Bu örnekte, Arac sınıfı birden fazla bileşenden (motor, depo, direksiyon, koltuk, ayna, teker) oluşarak composition (bileşim) kavramını temsil ediyor. 🧩 Her bileşen kendi sınıfında tanımlanmış ve Arac nesnesi bu parçaları kullanarak bir bütün oluşturmuş, yani “Arac bir Motor’a, Depo’ya, Direksiyon’a sahiptir” ilişkisi kurulmuş. 🔧 Böylece gerçek dünyadaki “parçadan bütüne” yaklaşımı, nesne tabanlı programlamada sınıflar arası bağımlılığı yönetilebilir hale getiriyor. 🚘✨
Bu yapı sayesinde her sınıf kendi sorumluluğunu taşır ve bağımsız olarak geliştirilebilir, bu da bakımı ve test edilebilirliği artırır. 🧠🔍
Arac sınıfı bir “bütün”ü temsil ederken, diğer sınıflar onun parçaları olarak davranır; bu da gerçek yaşam modellemesine oldukça yakındır. 🌍
Sonuç olarak composition, kalıtıma göre daha esnek bir yaklaşım sunar ve yazılımda “sahip olma (has-a)” ilişkisini doğal bir biçimde ifade eder. 💪🧱