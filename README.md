#PharmEasy App
This is a multi module maven application having following modules
### auth:
A generic approach to access validation using ACLs.
The module has following main actors/classes :
* User: Has requires permissions to access a Resource
* Resource: A resource protected via ACL
* ACLEntry: An entry identifying which user has what type of Permission on what Resource
* PermissionGrantingManager: A manager which check for access permissions and decides.

### app:
The main application specific entities, repos and services.
Patients medical records and precriptions are accessed using.
```
com.pharmeasy.app.service.PateintService
```
It depends on auth module for access control.

The demo package has a demo client to illustrate the working of the app.

