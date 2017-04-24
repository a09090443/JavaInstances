package ldap;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

public class LDAPExaminer {

	public static void main(String[] args) {
		LDAPExaminer ldapExaminer = new LDAPExaminer();
		// NOTE: replace theUserName below with the Active Directory/LDAP user
		// whose attribites you want printed.
		ldapExaminer.printUserBasicAttributes("zipe", ldapExaminer.getLdapContext());
	}

	public LdapContext getLdapContext() {
		LdapContext ctx = null;
		try {
			Hashtable env = new Hashtable();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");

			// NOTE: replace user@domain.com with a User that is present in your
			// Active Directory/LDAP
			env.put(Context.SECURITY_PRINCIPAL, "zipe@zipe.com");
			// NOTE: replace userpass with passwd of this user.
			env.put(Context.SECURITY_CREDENTIALS, "qweASDzxc123");
			// NOTE: replace ADorLDAPHost with your Active Directory/LDAP
			// Hostname or IP.
			env.put(Context.PROVIDER_URL, "ldap://192.168.0.8:389");

			System.out.println("Attempting to Connect...");

			ctx = new InitialLdapContext(env, null);
			System.out.println("Connection Successful.");
		} catch (NamingException nex) {
			System.out.println("LDAP Connection: FAILED");
			nex.printStackTrace();
		}
		return ctx;
	}

	private void printUserBasicAttributes(String username, LdapContext ctx) {
		try {

			SearchControls constraints = new SearchControls();
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			// NOTE: The attributes mentioned in array below are the ones that
			// will be retrieved, you can add more.
			String[] attrIDs = { "distinguishedName", "sn", "givenname", "mail", "telephonenumber", "canonicalName",
					"userAccountControl", "accountExpires" };
			constraints.setReturningAttributes(attrIDs);

			// NOTE: replace DC=domain,DC=com below with your domain info. It is
			// essentially the Base Node for Search.
			NamingEnumeration answer = ctx.search("DC=zipe,DC=com", "sAMAccountName=" + username, constraints);

			if (answer.hasMore()) {
				Attributes attrs = ((SearchResult) answer.next()).getAttributes();
				System.out.println(attrs.get("distinguishedName"));
				System.out.println(attrs.get("givenname"));
				System.out.println(attrs.get("sn"));
				System.out.println(attrs.get("mail"));
				System.out.println(attrs.get("telephonenumber"));
				System.out.println(attrs.get("canonicalName"));
				System.out.println(attrs.get("userAccountControl"));
				System.out.println(attrs.get("accountExpires"));
			} else {
				throw new Exception("Invalid User");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}