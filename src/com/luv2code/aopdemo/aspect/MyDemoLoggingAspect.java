package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.luv2code.aopdemo.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
// on veut maintenant recuperer les arguments et la signature méthode des pointcut 
// expressions pour s'en servir dans les aspects. Pour faire ça on utilise la classe 
// JoinPoint qui tient compte des metadonnées d'une expression pointcut.

	@Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		System.out.println("\n----------> Executing @Before advice on addAccount()");
		// afficher la signature de la methode
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		System.out.println("Method: " + methodSig);

		// afficher les args de la methode, pour ce faire:
		//// 1. choper les args
		Object[] args = theJoinPoint.getArgs();
		//// 2. boucler sur les args
		for (Object tempArg : args) {
			System.out.println(tempArg);

			if (tempArg instanceof Account) {
				// ici il faut "downcast" tempArg, parce que c'est un Objet générique pour qu'il
				// correspond aux arguments de Account

				Account theAccount = (Account) tempArg;
				System.out.println("account name: " + theAccount.getName());
				System.out.println("account level: " + theAccount.getLevel());

			}

		}
	}

	// PLUS DE TYPE D'ADVICE : en plus de @Before il y a @AfterReturning,
	// @AfterThrowing, @After et @Around. On s'occupe de @AfterReturning en premier;
	// il permet de rajouter du code une fois que la fonction annoté à été executé
	// avec succès, c'est-à-dire sans exceptions. C'est utlisé pourdu logging, de la
	// securité, des transactions, comme pour de l'audit logging (qui, quoi, quand,
	// où), mais le plus interessant, pour retravailler des données avant de les
	// rendre à l'émetteur de la demande (post-precessing, formatting, enrichment).
	// Exemple banal: en front on permet à l'utilisateur de saisir son numéro de tel
	// avec des espaces ou des points ou des parenthèses entre les chiffres, mais on
	// enlève ça avant de l'envoyer en BDD.
	// @AfterReturning s'utilise comme @Before mais en plus il faut déclarer les
	// valeurs de retour avec returning="maVariable". (On peut mettre n'importe quel
	// nom, ça reste au niveau de l'AOP, il feut juste mettre le même nom). Donc ça
	// s'écrit comme ça:

	// (@AfterReturning(pointcut="execution(chemin etc.)", returning="maVariable")
	// public void afterReturningResultFindAccountsAdvice(Joinpoint theJoinPoint,
	//// List<Account> maVariable)

	// 1. ajouter constructeurs à la classe Account
	// 2. ajouter nlle methode findAccounts() dans AccountDAO
	// 3. mettre à jour MainDemoAppqui appellera findAccounts()
	// 4. ajouter advice @AfterReturning
}
