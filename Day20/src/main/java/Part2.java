import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Part2 {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(new File("").getAbsolutePath() + "\\src\\main\\resources\\input.txt"));

        ArrayList<Particle> particles = new ArrayList<>();

        for (String line : lines ) {
            Particle newParticle = new Particle();
            String[] splitLine = line.split(">, ");
            String[] position = splitLine[0].replace("p=<", "").split(",");
            String[] velocity = splitLine[1].replace("v=<", "").split(",");
            String[] acceleration = splitLine[2].replace("a=<", "").replace(">", "").split(",");

            newParticle.x = Integer.parseInt(position[0]);
            newParticle.y = Integer.parseInt(position[1]);
            newParticle.z = Integer.parseInt(position[2]);

            newParticle.velX = Integer.parseInt(velocity[0]);
            newParticle.velY = Integer.parseInt(velocity[1]);
            newParticle.velZ = Integer.parseInt(velocity[2]);

            newParticle.accX = Integer.parseInt(acceleration[0]);
            newParticle.accY = Integer.parseInt(acceleration[1]);
            newParticle.accZ = Integer.parseInt(acceleration[2]);

            particles.add(newParticle);
        }

        int timeSinceLastCollision = 0;

        while (timeSinceLastCollision < 1000) {
            // Adjust all particle velocities, and then move each of them
            for (Particle particle : particles) {
                particle.velX += particle.accX;
                particle.velY += particle.accY;
                particle.velZ += particle.accZ;

                particle.x += particle.velX;
                particle.y += particle.velY;
                particle.z += particle.velZ;
            }

            // Check for collisions

            HashSet<Particle> collidedParticles = new HashSet<>();
            for (int currentParticleId = 0; currentParticleId < particles.size() - 1; currentParticleId++) {
                Particle currentParticle = particles.get(currentParticleId);
                for (int otherParticleId = currentParticleId + 1; otherParticleId < particles.size(); otherParticleId++) {
                    Particle otherParticle = particles.get(otherParticleId);
                    if (currentParticle.x == otherParticle.x && currentParticle.y == otherParticle.y && currentParticle.z == otherParticle.z) {
                        collidedParticles.add(currentParticle);
                        collidedParticles.add(otherParticle);
                    }
                }
            }

            if (collidedParticles.size() > 0) {
                for (Particle collidedParticle: collidedParticles) {
                    particles.remove(collidedParticle);
                }
            }


            timeSinceLastCollision++;
        }

        System.out.println("Done!");
    }


}
