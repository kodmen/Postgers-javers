# javers kullanımı

çalıştığım projede java spring boot audit(denetim) kullanmamız gerekti. Kısaca audit projemizde verilerimizi takip etmemizi sağlayan bir yöntem. verilerimiz kayıt olduğunda bunun tarihini tutan, güncellediğimizde bunun kim tarafında ne zaman olduğunu verisinini tutmamızı gerektiren bir veya sildiğmizde kimin sildiğini bize söyleyen bir sistem. bunun için en meşuru javers kutüphanesi. aslında projede zaten javers kullanılıyordu ben sadece öğrenmem gerektiği için öğrendim.

ilk aşama her zamanki gibi önce pom.xml dosyamıza kütüphanemizi ekleyelim. zamanı geldiğinide versiyonu güncellemenizi tavsiye ederim.

```xml
<dependency>
    <groupId>org.javers</groupId>
    <artifactId>javers-spring-boot-starter-sql</artifactId>
    <version>6.0.0</version>
</dependency>
```

bundan sonrası çok zor değil config dosyamızda kişinin tanımlanmasını yapmalıyız

```java
@Bean
    public AuthorProvider provideJaversAuthor() {
        return new SimpleAuthorProvider();
    }

    private static class SimpleAuthorProvider implements AuthorProvider {
        @Override
        public String provide() {
            return "Bedirhan Author";
        }
    }
```

burada kimin değişiklik yaptığını tanımlayabileceğimiz yer spring security ile verileri çekebileceğimiz yer
sonra hangi entitiyi takip etmek istiyorsan onun reposuna @JaversSpringDataAuditable bunu eklememiz lazım

```java
@JaversSpringDataAuditable
public interface AdresRepository extends JpaRepository<Adres, Long>
```
contorrelr sınıfında bunu yazınca api tarafından veriyi alabiliriz

```java
@GetMapping("/snapshots")
    public String getAdressSnapshot(){
        QueryBuilder jqlQuery = QueryBuilder.byClass(Adres.class);
        List<CdoSnapshot> snapshots = javers.findSnapshots(jqlQuery.build());
        return javers.getJsonConverter().toJson(snapshots);
    }
```

çıktı burda author bunu kimin yaptığını yazıyor. ve type tarafında hangi işlemin yaptığını yazar



```json 
[
  {
    "commitMetadata": {
      "author": "Baeldung Author",
      "properties": [],
      "commitDate": "2021-05-06T12:38:18.314",
      "commitDateInstant": "2021-05-06T09:38:18.314Z",
      "id": 6.00
    },
    "globalId": {
      "entity": "com.example.spring.entity.Adres",
      "cdoId": 15
    },
    "state": {
      "adresTip": "DIGER",
      "kisi": {
        "entity": "com.example.spring.entity.Kisi",
        "cdoId": 9
      },
      "id": 15,
      "adres": "string",
      "aktif": true
    },
    "changedProperties": [
      "adresTip",
      "kisi",
      "id",
      "adres",
      "aktif"
    ],
    "type": "INITIAL",
    "version": 1
  },
]
```

