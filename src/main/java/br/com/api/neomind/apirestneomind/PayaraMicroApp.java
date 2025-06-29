package br.com.api.neomind.apirestneomind;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Classe principal para executar a aplicação JavaEE com Payara Micro no IntelliJ IDEA
 * 
 * INSTRUÇÕES PARA USO NO INTELLIJ:
 * 
 * 1. PRIMEIRO: Compile o projeto
 *    - No IntelliJ: Build → Build Project
 *    - Ou no terminal: mvn clean package -DskipTests
 * 
 * 2. SEGUNDO: Execute esta classe
 *    - Clique com botão direito nesta classe → Run 'PayaraMicroApp.main()'
 *    - Ou use o ícone de play verde ao lado do método main
 * 
 * 3. TERCEIRO: Acesse a aplicação
 *    - Aplicação: http://localhost:8080/api
 *    - API Hello: http://localhost:8080/api/hello-world
 * 
 * 4. PARA PARAR: Clique no botão 'Stop' (quadrado vermelho) no IntelliJ
 * 
 * COMANDO MAVEN PARA COMMAND LINE (alternativa):
 * mvn clean package -DskipTests && mvn exec:java -Dexec.mainClass="br.com.api.neomind.apirestneomind.PayaraMicroApp"
 */
public class PayaraMicroApp {
    
    public static void main(String[] args) {
        try {
            System.out.println("========================================");
            System.out.println("🚀 API REST Neomind - Payara Micro");
            System.out.println("📋 JavaEE Application Server");
            System.out.println("========================================");
            
            // Verificar se o WAR foi compilado
            File warFile = new File("target/api.war");
            if (!warFile.exists()) {
                System.err.println("❌ ERRO: Arquivo target/api.war não encontrado!");
                System.err.println("");
                System.err.println("📋 SOLUÇÃO:");
                System.err.println("   1. No IntelliJ: Build → Build Project");
                System.err.println("   2. Ou no terminal: mvn clean package -DskipTests");
                System.err.println("   3. Execute esta classe novamente");
                System.err.println("");
                System.exit(1);
            }
            
            System.out.println("📦 WAR encontrado: " + warFile.getAbsolutePath());
            System.out.println("🔧 Procurando Payara Micro...");

            // Encontrar o JAR do Payara Micro no repositório Maven local
            String userHome = System.getProperty("user.home");
            Path payaraJar = Paths.get(userHome, ".m2", "repository", "fish", "payara", "extras", "payara-micro", "6.2024.2", "payara-micro-6.2024.2.jar");

            if (!payaraJar.toFile().exists()) {
                System.err.println("❌ ERRO: Payara Micro não encontrado!");
                System.err.println("📋 Execute primeiro: mvn dependency:resolve");
                System.err.println("📍 Caminho esperado: " + payaraJar);
                System.exit(1);
            }

            System.out.println("✅ Payara Micro encontrado: " + payaraJar);
            System.out.println("🚀 Iniciando servidor com context root /api...");

            // Executar Payara Micro via ProcessBuilder com context root correto
            ProcessBuilder pb = new ProcessBuilder(
                "java",
                "-jar", payaraJar.toString(),
                "--deploy", warFile.getAbsolutePath(),
                "--contextroot", "/api",
                "--port", "8080"
            );

            pb.inheritIO(); // Redirecionar output para o console do IntelliJ
            Process process = pb.start();

            // Configurar shutdown hook para parar o processo
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\n🛑 Parando aplicação...");
                if (process.isAlive()) {
                    process.destroy();
                    try {
                        // Aguardar 5 segundos para parada graceful
                        if (!process.waitFor(5, java.util.concurrent.TimeUnit.SECONDS)) {
                            System.out.println("⚡ Forçando parada do processo...");
                            process.destroyForcibly();
                        }
                    } catch (InterruptedException e) {
                        process.destroyForcibly();
                    }
                }
                System.out.println("✅ Aplicação parada com sucesso!");
            }));
            
            System.out.println("========================================");
            System.out.println("✅ APLICAÇÃO INICIADA COM SUCESSO!");
            System.out.println("========================================");
            System.out.println("🌐 URLs disponíveis:");
            System.out.println("   🏠 Aplicação: http://localhost:8080/api");
            System.out.println("   🔗 Hello API: http://localhost:8080/api/hello-world");
            System.out.println("   📋 WADL: http://localhost:8080/api/application.wadl");
            System.out.println("========================================");
            System.out.println("⏹️  Para parar: Clique no botão 'Stop' no IntelliJ");
            System.out.println("🐛 Para debug: Use o botão 'Debug' no IntelliJ");
            System.out.println("🔄 Para recarregar: Pare e execute novamente");
            System.out.println("========================================");
            
            System.out.println("⏳ Servidor rodando... aguardando requisições.");
            System.out.println("💡 Dica: Teste a API acessando http://localhost:8080/api/hello-world");

            // Aguardar o processo terminar
            int exitCode = process.waitFor();
            System.out.println("🔚 Aplicação finalizada com código: " + exitCode);

        } catch (InterruptedException e) {
            System.out.println("🛑 Aplicação interrompida pelo usuário.");
        } catch (IOException e) {
            System.err.println("❌ ERRO ao iniciar a aplicação:");
            e.printStackTrace();
            System.err.println("");
            System.err.println("🔧 POSSÍVEIS SOLUÇÕES:");
            System.err.println("   1. Verifique se o JAVA_HOME está configurado");
            System.err.println("   2. Verifique se a porta 8080 não está em uso");
            System.err.println("   3. Execute: mvn clean package -DskipTests");
            System.err.println("   4. Tente executar novamente");
            System.exit(1);
        }
    }
}
