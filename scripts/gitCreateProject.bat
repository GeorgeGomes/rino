
#ignore target folder
git rm -r --cached target

git init 
git add . 
git commmit -m "Commit" 
git remote add origin https://github.com/GeorgeGomes/rino.git
git push -u origin master
