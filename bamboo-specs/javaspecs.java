import com.atlassian.bamboo.specs.api.BambooSpec;
import com.atlassian.bamboo.specs.api.builders.BambooKey;
import com.atlassian.bamboo.specs.api.builders.BambooOid;
import com.atlassian.bamboo.specs.api.builders.applink.ApplicationLink;
import com.atlassian.bamboo.specs.api.builders.permission.PermissionType;
import com.atlassian.bamboo.specs.api.builders.permission.Permissions;
import com.atlassian.bamboo.specs.api.builders.permission.PlanPermissions;
import com.atlassian.bamboo.specs.api.builders.plan.Job;
import com.atlassian.bamboo.specs.api.builders.plan.Plan;
import com.atlassian.bamboo.specs.api.builders.plan.PlanIdentifier;
import com.atlassian.bamboo.specs.api.builders.plan.Stage;
import com.atlassian.bamboo.specs.api.builders.plan.artifact.Artifact;
import com.atlassian.bamboo.specs.api.builders.plan.branches.BranchCleanup;
import com.atlassian.bamboo.specs.api.builders.plan.branches.PlanBranchManagement;
import com.atlassian.bamboo.specs.api.builders.plan.configuration.ConcurrentBuilds;
import com.atlassian.bamboo.specs.api.builders.project.Project;
import com.atlassian.bamboo.specs.api.builders.repository.VcsChangeDetection;
import com.atlassian.bamboo.specs.builders.repository.bitbucket.server.BitbucketServerRepository;
import com.atlassian.bamboo.specs.builders.repository.viewer.BitbucketServerRepositoryViewer;
import com.atlassian.bamboo.specs.builders.task.CheckoutItem;
import com.atlassian.bamboo.specs.builders.task.NpmTask;
import com.atlassian.bamboo.specs.builders.task.SshTask;
import com.atlassian.bamboo.specs.builders.task.VcsCheckoutTask;
import com.atlassian.bamboo.specs.util.BambooServer;

@BambooSpec
public class PlanSpec {
    
    public Plan plan() {
        final Plan plan = new Plan(new Project()
                .oid(new BambooOid(""))
                .key(new BambooKey("ONE"))
                .name("onlyone"),
            "NEWW",
            new BambooKey("newplan"))
            .oid(new BambooOid(""))
            .pluginConfigurations(new ConcurrentBuilds()
                    .useSystemWideDefault(false))
            .stages(new Stage("Stage 1")
                    .jobs(new Job("Clone and install",
                            new BambooKey("CI"))
                          /*  .artifacts(new Artifact()
                                    .name("conn")
                                    .copyPattern("**")
                                    .shared(true))
                            .tasks(new VcsCheckoutTask()
                                    .checkoutItems(new CheckoutItem().defaultRepository()),
                                new NpmTask()
                                    .nodeExecutable("Node.js")
                                    .command("install"),
                                new SshTask().authenticateWithPassword("BAMSCRT@0@0@2HRa550lwZh2SVJuyaVDUYJxt97VVbSdYlcYjWzpWec=")
                                    .host("23.101.140.72")
                                    .username("rig")
                                    .command("cd SampleNodeApp\r\nls"))))
            .planRepositories(new BitbucketServerRepository()
                    .name("sample nodeApp")
                    .oid(new BambooOid("slxbyd2i8m7e"))
                    .repositoryViewer(new BitbucketServerRepositoryViewer())
                    .server(new ApplicationLink()
                            .name("Bitbucket")
                            .id("d77c4959-54e1-3e9a-bff3-253dbb5586ee"))
                    .projectKey("US")
                    .repositorySlug("sample-nodeapp")
                    .sshPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCvltOnRcwblZSxl1htX0XGTW08Th8GbiAHG6RtRDvmr6M/1+xAU5WsKDBZX4HiSUue7ZUoxbBQyrQp5GVt99UG6c5Q0AW2ElMVMKMzHdJRXFcVX4V3JHr19wZVtEdPXfFsvm/DwH+Vp5uSqe8MMXpj5r8wpHc712CZ5ybdZbtp2s2H0V8F+hmgaV2zfufd3+CY+pKRL6dH5TVePW8wE9PQOUJ9FXnKnMW8VXWC0yd9gFDuQuVoibi6uXWjPH2jTrRC/EYmzTDOZm3f1c/OMc9KlGXr9Opi+q0IaM+JljML+ialNEXiF3wvNJTzd+SjkAohWak7uArU6kfx7V4Jv+UF http://18.220.222.179:8085")
                    .sshPrivateKey("-----BEGIN RSA PRIVATE KEY-----\nMIIEogIBAAKCAQEAr5bTp0XMG5WUsZdYbV9Fxk1tPE4fBm4gBxukbUQ75q+jP9fs\nQFOVrCgwWV+B4klLnu2VKMWwUMq0KeRlbffVBunOUNAFthJTFTCjMx3SUVxXFV+F\ndyR69fcGVbRHT13xbL5vw8B/laebkqnvDDF6Y+a/MKR3O9dgmecm3WW7adrNh9Ff\nBfoZoGlds37n3d/gmPqSkS+nR+U1Xj1vMBPT0DlCfRV5ypzFvFV1gtMnfYBQ7kLl\naIm4url1ozx9o060QvxGJs0wzmZt39XPzjHPSpRl6/TqYvqtCGjPiZYzC/ompTRF\n4hd8LzSU83fko5AKIVmpO7gK1OpH8e1eCb/lBQIDAQABAoIBAA0wyC0ed+8RjVZK\n/4xV1iTHv79CQh/mSXcnOzKvJ83UX2/mHVwWu11vtnQcUeJJLcpeYDBsK2riU2b7\nPqhkcAchLMvS2L79AOtMWFI4eQp+JJeZMapJ3gfYuwvYq2u5q6+G3dgHf74jFXOO\n2wOgOujC1pWFoWRoMVxWakvC/vF8a8jcn9J+l8F7GflZ8KR3adOJb0csgLNDEaBw\nR+OgVkzrX1DO7ceAWKGkCsAN4rNAGO9kkFTy0ND/3bCyVWcYq7UbAFdDHv8MfDkm\ncRVBjWH7YvpvrtIO7atflaK9Zv67tcySEBevAuwmMhoTiXG5Rz49VuzgM2OvPf+A\n22AIpjECgYEA26i8uWVaGN2yxp6Ql5t+jeEoMFSdq+cFxgRU+LkdcgaI0AQVj498\nlRGTj/G6HAz9BYfJMd5LIyDuyty0Oaf6V4yGWeHSM/8IP7Yddka7fR4DduRHnHlE\nzV2QjXI6UpMb4/MBqSdVuPnkHza5/2ppORJorlWLPRweQHyf9844fDECgYEAzKOW\nI4w/A2vy3xNgHKFGIDeoo4po75mdY9v11dI4b7xrtNNwEgoIbtuFzjbwXm88h0OB\ntdpxqKeZx2kGfGdGH/bqHZodzMSUSITdRPDKWxcJs5egIq1J4z1Syph355a+VPwe\nyt1106Gezt++CY5xbDhQh/jSo1LFDUhA4xlMxRUCgYBk/HSXf6MmX3Z7nha3vMM4\nE1XYsm4yw+mBmig+mUySSbLIf7gx4jPSjNwch3OaxdXlnwG+rYY5xkBCf6lUAIg1\ni+OQmyN6yDtQnzd4UxiLcysAk/7gT1h0WhnFDckGgZ+G8wfGbtEYU7qvJwfoB1IM\n7Qu3apizJaQ63ZDl6qdJYQKBgEMnA2PS5/K1f9OYRhffDGYSHbLc/qZxoGZejgOG\noqJ4EFgcclBZHn9SZkxPVv2GPzScAHqunlGb/iij3E8CjM58dkMUiebiM07A2uN+\nqMjPfI2EmjYG65I4U2zML7y9iOPWgDxOBBZYmQaGAZSdO7cpm7OjAWw7AxLivPhJ\nGxopAoGAWvSotm7EmypbiV8gOZQx83OeYbHDarQH9yZSjxsejOvQRhANy+0bDNKh\n/11Vr7cHEti0AYZJcHOFaykc8cpP+GeA2Htu4aF9+8UVSmH2cwpPlZMxXWOpkAaz\nKr7yndKA3jWsYlshv9DsPfxsgaGGZvUdea+9Auu0vZl8KI2rl5U=\n-----END RSA PRIVATE KEY-----\n")
                    .sshCloneUrl("ssh://git@ec2-18-224-68-30.us-east-2.compute.amazonaws.com:7999/us/sample-nodeapp.git")
                    .changeDetection(new VcsChangeDetection()))
            
            .planBranchManagement(new PlanBranchManagement()
                    .delete(new BranchCleanup()))
            .forceStopHungBuilds();
        return plan;
    }
    */
    public PlanPermissions planPermission() {
        final PlanPermissions planPermission = new PlanPermissions(new PlanIdentifier("SAM", "CONN"))
            .permissions(new Permissions()
                    .userPermissions("rig", PermissionType.ADMIN, PermissionType.VIEW, PermissionType.CLONE, PermissionType.BUILD, PermissionType.EDIT)
                    .anonymousUserPermissionView());
        return planPermission;
    }
    
    public static void main(String... argv) {
        //By default credentials are read from the '.credentials' file.
        BambooServer bambooServer = new BambooServer("http://18.220.222.179:8085");
        final PlanSpec planSpec = new PlanSpec();
        
        final Plan plan = planSpec.plan();
        bambooServer.publish(plan);
        
        final PlanPermissions planPermission = planSpec.planPermission();
        bambooServer.publish(planPermission);
    }
}
