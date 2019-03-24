import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Part1 {

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

        int slowestVeloctiy = Integer.MAX_VALUE;
        int slowestParticle = 0;

        for (int i = 0; i < particles.size(); i++) {
            Particle currentParticle = particles.get(i);
            int particleVelocity = Math.abs(currentParticle.accX) + Math.abs(currentParticle.accY) + Math.abs(currentParticle.accZ);
            if (particleVelocity < slowestVeloctiy) {
                slowestVeloctiy = particleVelocity;
                slowestParticle = i;
            }

        }

        System.out.println(slowestParticle);
        System.out.println("Done!");
    }


}
