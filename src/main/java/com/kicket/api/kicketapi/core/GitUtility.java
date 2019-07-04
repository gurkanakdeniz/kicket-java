package com.kicket.api.kicketapi.core;

import java.io.File;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import com.kicket.api.kicketapi.configuration.GitProperties;

public class GitUtility {

    private String user;
    private String pass;
    private String remote;
    private String branch;
    private String directory;
    private static final int timeout = 60;

    private CredentialsProvider cp;
    private Git git;

    public GitUtility(GitProperties prop, boolean clone) throws Exception {
        user = prop.getUser();
        pass = prop.getPass();
        remote = prop.getRemote();
        branch = prop.getBranch();
        directory = prop.getDirectory();
        
        cp = new UsernamePasswordCredentialsProvider(user, pass);
        
        if (clone) {
            remoteClone();
        } else {
            git = Git.init().setDirectory(new File(directory)).call();
        }
    }

    private void remoteClone() throws Exception {

        try {
            try {
                FileUtility.deleteFolder("");
            } catch (Exception e) {
                e.printStackTrace();
            }

            git = Git.cloneRepository().setURI(remote).setTimeout(timeout).setDirectory(new File(directory))
                    .setBranch(branch).setCloneAllBranches(false).setCredentialsProvider(cp).call();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void remoteAdd() {
        try {
            AddCommand add = git.add();
            add.addFilepattern(".");
            add.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remoteAdd(String pattern) {
        try {
            AddCommand add = git.add();
            add.addFilepattern(pattern);
            add.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remoteCommit(String message) {
        try {
            CommitCommand commit = git.commit();
            commit.setMessage(message);
            commit.setCredentialsProvider(cp);
            commit.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void remotePush() {
        try {
            PushCommand push = git.push();
            push.setCredentialsProvider(cp);
            push.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void remotePush(String pattern, String message) {
        try {
            remoteAdd(pattern);
            remoteCommit(message);
            remotePush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remoteAllPush(String commitMessage) {
        try {
            remoteAdd();
            remoteCommit(commitMessage);
            remotePush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remotePull() throws Exception {
        try {
            git.pull().setCredentialsProvider(cp).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
