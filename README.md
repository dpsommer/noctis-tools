noctis-tools
============

Git repo for deployment to Heroku, contains a basic website built to house tools for ArcheAge guild Noctis.

Built on a mongodb, Java/SpringMVC, Angularjs stack with .jade templating.

<h3>Deployment to Heroku</h3>

Clone this repo:
<code>git clone https://github.com/dpsommer/noctis-tools</code>

(or fork it on github)

Download the heroku toolbar <a href=https://toolbelt.heroku.com/windows>here</a>.

Once you have your heroku account, log in with <code>heroku login</code> from the command line.

You'll probably need to create an ssh key, which you can do from git-bash with <code>ssh-keygen -t rsa</code>, or via the heroku login prompts.

You can either create your own heroku app to use as a testing environment and add it as a branch to be merged, or request permission to push directly to the main heroku app.

In your cloned directory, you can create a new remote for the main heroku app with<br/>
<code>git remote add heroku git@heroku.com:noctis-tools.git</code>


This project uses Amazon s3 for static content, please reference any large content files from there: <a>https://s3.amazonaws.com/noctis-tools-assets/</a>

The Heroku toolbar comes with the foreman Ruby gem, which allows you to deploy a test version of the site to localhost:5000 using the .pf-local Procfile with <code>foreman start -f .pf-local</code>

Note that .pf-local is specifically for Windows, for Linux, just run <code>foreman start</code> to use the default Procfile.
