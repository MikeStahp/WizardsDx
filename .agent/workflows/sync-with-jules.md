---
description: Sync changes with Jules (git pull/push)
---
# Sync with Jules
This workflow ensures your local environment is synchronized with the remote repository where Jules may have pushed changes.

1. **Check Status**
   Check if there are any uncommitted local changes.
   ```bash
   git status
   ```

2. **Pull Changes**
   // turbo
   Pull the latest changes from the remote repository.
   ```bash
   git pull
   ```

3. **Push Changes (Optional)**
   If you have completed work and committed it, push to remote.
   *Note: Ensure you have added and committed your changes before running this step manually.*
   ```bash
   git push
   ```
